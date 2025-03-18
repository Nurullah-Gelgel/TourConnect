package com.TourConnect.TourConnect.infrastructure.providers;


import com.iyzipay.Options;
import com.iyzipay.model.*;
import com.iyzipay.request.CreatePaymentRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class IyzicoPaymentProvider {

    private final Options iyzicoOptions;

    public IyzicoPaymentProvider(Options iyzicoOptions) {
        this.iyzicoOptions = iyzicoOptions;
    }

    public String processPayment(BigDecimal amount, String currency) {
        CreatePaymentRequest request = new CreatePaymentRequest();
        request.setLocale("tr");
        request.setConversationId(UUID.randomUUID().toString());
        request.setPrice(amount);
        request.setPaidPrice(amount);
        request.setCurrency(currency);
        request.setInstallment(1);
        request.setBasketId("B12345");
        request.setPaymentChannel("WEB");
        request.setPaymentGroup("PRODUCT");

        // Ödeme kartı bilgileri (Test kartı)
        PaymentCard paymentCard = new PaymentCard();
        paymentCard.setCardHolderName("John Doe");
        paymentCard.setCardNumber("5528790000000008");
        paymentCard.setExpireMonth("12");
        paymentCard.setExpireYear("2030");
        paymentCard.setCvc("123");
        paymentCard.setRegisterCard(0);
        request.setPaymentCard(paymentCard);

        // Kullanıcı bilgileri
        Buyer buyer = new Buyer();
        buyer.setId("BY789");
        buyer.setName("John");
        buyer.setSurname("Doe");
        buyer.setGsmNumber("+905350000000");
        buyer.setEmail("john.doe@example.com");
        buyer.setIdentityNumber("74300864791");
        buyer.setLastLoginDate("2025-03-17 12:00:00");
        buyer.setRegistrationDate("2023-03-17 12:00:00");
        buyer.setRegistrationAddress("İstanbul, Türkiye");
        buyer.setIp("85.34.78.112");
        buyer.setCity("İstanbul");
        buyer.setCountry("Turkey");
        request.setBuyer(buyer);

        // Adres bilgileri
        Address shippingAddress = new Address();
        shippingAddress.setContactName("John Doe");
        shippingAddress.setCity("İstanbul");
        shippingAddress.setCountry("Turkey");
        shippingAddress.setAddress("Test mahallesi, İstanbul");
        request.setShippingAddress(shippingAddress);

        Address billingAddress = new Address();
        billingAddress.setContactName("John Doe");
        billingAddress.setCity("İstanbul");
        billingAddress.setCountry("Turkey");
        billingAddress.setAddress("Test mahallesi, İstanbul");
        request.setBillingAddress(billingAddress);

        // Ürün bilgileri
        List<BasketItem> basketItems = new ArrayList<>();
        BasketItem basketItem = new BasketItem();
        basketItem.setId("BI101");
        basketItem.setName("Otel Rezervasyonu");
        basketItem.setCategory1("Turizm");
        basketItem.setItemType(BasketItemType.VIRTUAL.name());
        basketItem.setPrice(amount);
        basketItems.add(basketItem);
        request.setBasketItems(basketItems);

        // Ödeme işlemini başlat
        Payment payment = Payment.create(request, iyzicoOptions);

        if (payment.getStatus().equals("success")) {
            return payment.getConversationId();
        } else {
            throw new RuntimeException("Ödeme başarısız: " + payment.getErrorMessage());
        }
    }
}