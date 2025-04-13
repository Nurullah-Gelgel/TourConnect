package com.TourConnect.TourConnect.application.dtos;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponseDto {
    private UUID id;
    private String status;
    private Double totalAmount;
    private Integer reservationGuests;
    private String pnrCode;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private LocalDate reservationCreatedAt;
    private String guestName;
    private String guestEmail;
    private String guestPhone;
    private String specialRequests;
    private String roomType;
    private Integer roomCount;
    private String hotelName;
    private UUID paymentId;

}
