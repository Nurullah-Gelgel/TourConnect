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

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PaymentServiceImpl  implements IPaymentService {

    private final PaymentRepository paymentRepository;
    private final ReservationRepository reservationRepository;
    private final PaymentMapper paymentMapper;
    private final IyzicoPaymentProvider iyzicoPaymentProvider;
    private final ZarinpalPaymentProvider zarinpalPaymentProvider;
    private final StripePaymentProvider stripePaymentProvider;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository,ReservationRepository reservationRepository, PaymentMapper paymentMapper, IyzicoPaymentProvider iyzicoPaymentProvider, ZarinpalPaymentProvider zarinpalPaymentProvider, StripePaymentProvider stripePaymentProvider) {
        this.paymentRepository = paymentRepository;
        this.reservationRepository = reservationRepository;
        this.paymentMapper = paymentMapper;
        this.iyzicoPaymentProvider = iyzicoPaymentProvider;
        this.zarinpalPaymentProvider = zarinpalPaymentProvider;
        this.stripePaymentProvider = stripePaymentProvider;
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
}
