package com.TourConnect.TourConnect.application.dtos;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDto {

    private UUID reservationId;
    private String status;
    private Double totalAmount;
    private Integer reservationGuests;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private String reservationCreatedAt;
    private String reservationUpdatedAt;

    private UUID hotelId;
    private UUID userId;
    private UUID roomID;
}
