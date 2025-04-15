package com.TourConnect.TourConnect.application.services;

import com.TourConnect.TourConnect.application.dtos.PaymentDto;
import com.TourConnect.TourConnect.application.mappers.PaymentMapper;
import com.TourConnect.TourConnect.domain.entities.Payment;
import com.TourConnect.TourConnect.domain.entities.PaymentStatus;
import com.TourConnect.TourConnect.domain.entities.Reservation;
import com.TourConnect.TourConnect.domain.repositories.PaymentRepository;
import com.TourConnect.TourConnect.domain.repositories.ReservationRepository;
import com.TourConnect.TourConnect.infrastructure.providers.IyzicoPaymentProvider;
import com.TourConnect.TourConnect.infrastructure.providers.StripePaymentProvider;
import com.TourConnect.TourConnect.infrastructure.providers.ZarinpalPaymentProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl  implements IPaymentService {

    private final PaymentRepository paymentRepository;
    private final ReservationRepository reservationRepository;
    private final PaymentMapper paymentMapper;
    private final IyzicoPaymentProvider iyzicoPaymentProvider;
    private final ZarinpalPaymentProvider zarinpalPaymentProvider;
    private final StripePaymentProvider stripePaymentProvider;
    private final FileStorageService fileStorageService;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, ReservationRepository reservationRepository, PaymentMapper paymentMapper, IyzicoPaymentProvider iyzicoPaymentProvider, ZarinpalPaymentProvider zarinpalPaymentProvider, StripePaymentProvider stripePaymentProvider, FileStorageService fileStorageService) {
        this.paymentRepository = paymentRepository;
        this.reservationRepository = reservationRepository;
        this.paymentMapper = paymentMapper;
        this.iyzicoPaymentProvider = iyzicoPaymentProvider;
        this.zarinpalPaymentProvider = zarinpalPaymentProvider;
        this.stripePaymentProvider = stripePaymentProvider;
        this.fileStorageService = fileStorageService;
    }

    @Transactional
    @Override
    public PaymentDto createPayment(PaymentDto paymentDto) {
        // Ödeme sağlayıcısı seçimi ve işlem
        String transactionId;
        if (paymentDto.getCurrency().equalsIgnoreCase("TRY")) {
            transactionId = iyzicoPaymentProvider.processPayment(paymentDto.getAmount(), paymentDto.getCurrency());
        } else if (paymentDto.getCurrency().equalsIgnoreCase("IRR")) {
            transactionId = zarinpalPaymentProvider.processPayment(paymentDto.getAmount(), paymentDto.getCurrency());
        } else if (paymentDto.getCurrency().equalsIgnoreCase("USD")) {
            transactionId = stripePaymentProvider.processPayment(paymentDto.getAmount(), paymentDto.getCurrency());
        } else {
            throw new IllegalArgumentException("Desteklenmeyen para birimi: " + paymentDto.getCurrency());
        }

        // Rezervasyon kontrolü
        if (paymentDto.getReservationId() == null) {
            throw new IllegalArgumentException("Rezervasyon ID zorunludur!");
        }

        // Rezervasyonu bul
        Reservation reservation = reservationRepository.findById(paymentDto.getReservationId())
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        // Payment entity oluştur
        Payment payment = new Payment();
        payment.setAmount(paymentDto.getAmount());
        payment.setCurrency(paymentDto.getCurrency());
        payment.setStatus(PaymentStatus.PENDING);
        payment.setProvider(paymentDto.getProvider());
        payment.setReservation(reservation);
        payment.setTransactionId(transactionId);
        payment.setPaymentDate(LocalDateTime.now());

        // Kaydet ve DTO'ya dönüştür
        Payment savedPayment = paymentRepository.save(payment);
        return paymentMapper.toDto(savedPayment);
    }



    @Override
    public PaymentDto getPaymentByTransactionId(String transactionId) {
        return paymentRepository.findByTransactionId(transactionId)
                .map(paymentMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
    }


    @Override
    public List<PaymentDto> getAllPayments() {
        return paymentRepository.findAll().stream().map(paymentMapper::toDto).collect(Collectors.toList());

    }

    @Override
    public PaymentDto getPaymentById(UUID id) {
        return paymentRepository.findById(id).map(paymentMapper::toDto).orElseThrow(() -> new RuntimeException("Payment not found"));

    }

    @Override
    public PaymentDto updatePayment(UUID id, PaymentDto dto) {
        Payment paymentToUpdate = paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("Payment not found"));
        paymentMapper.updateEntity(dto, paymentToUpdate);
        return paymentMapper.toDto(paymentRepository.save(paymentToUpdate));
    }

    @Override
    public void deletePayment(UUID id) {
        if (paymentRepository.findById(id).isPresent()) {
            paymentRepository.deleteById(id);
        } else {
            throw new RuntimeException("Payment not found");
        }
    }
    public void updateReceiptPath(UUID paymentId, String filePath) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment bulunamadı: " + paymentId));

        payment.setReceipt(filePath);
        paymentRepository.save(payment);
    }


    @Override
    public void uploadReceipt(UUID paymentId, MultipartFile receiptFile) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        String receiptUrl = fileStorageService.store(receiptFile);

        payment.setReceipt(receiptUrl);
        payment.setStatus(PaymentStatus.RECEIPT_UPLOADED);
        paymentRepository.save(payment);
    }

    @Override
    public void verifyReceipt(UUID paymentId, boolean approve) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        if (approve) {
            payment.setStatus(PaymentStatus.COMPLETED);
            payment.setIsReceiptVerified(true);
        } else {
            payment.setStatus(PaymentStatus.REJECTED);
            payment.setIsReceiptVerified(false);
        }

        paymentRepository.save(payment);
    }


}
