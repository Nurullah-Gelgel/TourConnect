package com.TourConnect.TourConnect.presentation.controllers;

import com.TourConnect.TourConnect.application.dtos.PaymentDto;
import com.TourConnect.TourConnect.application.services.IPaymentService;
import com.TourConnect.TourConnect.application.services.PaymentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final IPaymentService paymentService;

    @PostMapping("/create")
    public ResponseEntity<PaymentDto> createPayment(@RequestBody PaymentDto paymentDto) {
        return ResponseEntity.ok(paymentService.createPayment(paymentDto));
    }

    @GetMapping("/getAllPayments")
    public ResponseEntity<List<PaymentDto>> getAllPayments() {
        List<PaymentDto> payments = paymentService.getAllPayments();
        return ResponseEntity.ok(payments);
    }

    // 🔹 2. GET: ID ile ödeme getir
    @GetMapping("getPaymentById/{id}")
    public ResponseEntity<PaymentDto> getPaymentById(@PathVariable UUID id) {
        PaymentDto payment = paymentService.getPaymentById(id);
        return ResponseEntity.ok(payment);
    }

    // 🔹 3. PUT: Ödeme güncelle
    @PutMapping("updatePayment/{id}")
    public ResponseEntity<PaymentDto> updatePayment(
            @PathVariable UUID id,
            @RequestBody PaymentDto paymentDTO
    ) {
        PaymentDto updatedPayment = paymentService.updatePayment(id, paymentDTO);
        return ResponseEntity.ok(updatedPayment);
    }

    // 🔹 4. DELETE: Ödeme sil
    @DeleteMapping("deletePayment/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable UUID id) {
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<PaymentDto> getPayment(@PathVariable String transactionId) {
        return ResponseEntity.ok(paymentService.getPaymentByTransactionId(transactionId));
    }

    @PostMapping("/{id}/verify")
    public ResponseEntity<?> verifyReceipt(@PathVariable UUID id, @RequestParam boolean approve){
        paymentService.verifyReceipt(id, approve);
        return ResponseEntity.ok().build();
    }


}