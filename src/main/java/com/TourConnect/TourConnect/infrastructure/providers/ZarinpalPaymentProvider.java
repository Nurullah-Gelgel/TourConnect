package com.TourConnect.TourConnect.infrastructure.providers;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Service
public class ZarinpalPaymentProvider {

    public String processPayment(BigDecimal amount, String currency) {
        // Burada gerçek Zarinpal API çağrısı olacak
        System.out.println("Zarinpal üzerinden ödeme işleniyor: " + amount + " " + currency);
        return "ZARINPAL_TXN_" + System.currentTimeMillis();
    }
}
