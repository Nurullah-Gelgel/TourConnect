package com.TourConnect.TourConnect.application.dtos;

import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {
    private UUID id;
    private String roomType;
    private String roomDescription;
    private Integer roomCapacity;
    private Double roomPrice;
    private String roomStatus;
    private UUID hotelId;
}
