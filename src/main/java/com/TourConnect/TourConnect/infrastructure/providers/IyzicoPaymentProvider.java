package com.TourConnect.TourConnect.infrastructure.providers;



import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class IyzicoPaymentProvider {



    public String processPayment(BigDecimal amount, String currency) {
        System.out.println("İyzico üzerinden ödeme işleniyor: " + amount + " " + currency);
        return "STRIPE_TXN_" + System.currentTimeMillis();
    }
}