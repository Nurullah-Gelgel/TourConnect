package com.TourConnect.TourConnect.application.dtos;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class  ReservationDto {


    private UUID id;

    private String status;

    private Double totalAmount;

    private Integer reservationGuests;

    private LocalDate checkIn;

    private LocalDate checkOut;

    private LocalDate reservationCreatedAt;

    private LocalDate reservationUpdatedAt;

    private String guestName;

    private UUID userId;

    private String guestEmail;

    private String guestPhone;

    private String specialRequests;

    private String roomType;
    private Integer roomCount;

    private UUID hotelId;

    private UUID paymentId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double advancePayment;
}
