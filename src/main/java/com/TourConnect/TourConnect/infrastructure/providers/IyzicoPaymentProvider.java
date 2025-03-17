package com.TourConnect.TourConnect.infrastructure.providers;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Service
public class IyzicoPaymentProvider {

    public String processPayment(BigDecimal amount, String currency) {
        // Burada gerçek Iyzico API çağrısı olacak
        System.out.println("Iyzico üzerinden ödeme işleniyor: " + amount + " " + currency);
        return "IYZICO_TXN_" + System.currentTimeMillis();
    }
}
