package com.TourConnect.TourConnect.application.services;

import com.TourConnect.TourConnect.application.dtos.PaymentDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface IPaymentService {

    PaymentDto createPayment(PaymentDto paymentDto);
    PaymentDto getPaymentByTransactionId(String transactionId);

    List<PaymentDto> getAllPayments();
    PaymentDto getPaymentById(UUID id);
    PaymentDto updatePayment(UUID id, PaymentDto dto);
    void deletePayment(UUID id);

    void uploadReceipt(UUID paymentId, MultipartFile receiptFile);
    void verifyReceipt(UUID paymentId, boolean approve);

    void updateReceiptPath(UUID paymentId, String filePath);
}
