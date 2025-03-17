package com.TourConnect.TourConnect.presentation.controllers;

import com.TourConnect.TourConnect.application.dtos.PaymentDto;
import com.TourConnect.TourConnect.application.services.PaymentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentServiceImpl paymentService;

    @PostMapping("/create")
    public ResponseEntity<PaymentDto> createPayment(@RequestBody PaymentDto paymentDto) {
        return ResponseEntity.ok(paymentService.createPayment(paymentDto));
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<PaymentDto> getPayment(@PathVariable String transactionId) {
        return ResponseEntity.ok(paymentService.getPaymentByTransactionId(transactionId));
    }
}