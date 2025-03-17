package com.TourConnect.TourConnect.application.dtos;

import com.TourConnect.TourConnect.domain.entities.PaymentStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {

    private UUID id;

    private BigDecimal amount;

    private PaymentStatus status;

    private String currency;

    private String transactionId;

    private String provider; // "Iyzico" veya "IranPay"

    private LocalDateTime paymentDate;

    private UUID reservationId;

}
