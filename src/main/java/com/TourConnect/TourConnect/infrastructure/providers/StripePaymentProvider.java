package com.TourConnect.TourConnect.infrastructure.providers;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class StripePaymentProvider {
    public String processPayment(BigDecimal amount, String currency) {
        System.out.println("Stripe üzerinden ödeme işleniyor: " + amount + " " + currency);
        return "STRIPE_TXN_" + System.currentTimeMillis();
    }
}