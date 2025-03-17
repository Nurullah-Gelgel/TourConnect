package com.TourConnect.TourConnect.application.services;

import com.TourConnect.TourConnect.application.dtos.PaymentDto;

import java.util.UUID;

public interface IPaymentService {

    PaymentDto createPayment(PaymentDto paymentDto);
    PaymentDto getPaymentByTransactionId(String transactionId);
}
