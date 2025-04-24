package com.TourConnect.TourConnect.application.dtos;

import com.TourConnect.TourConnect.domain.entities.Hotel;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomTypeDto {


    private UUID id;

    private String name;

    private Double pricePerNight;

    private UUID hotelId;
}
