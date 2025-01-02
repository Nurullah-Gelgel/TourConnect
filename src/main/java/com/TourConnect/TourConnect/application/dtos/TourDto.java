package com.TourConnect.TourConnect.application.dtos;

import java.time.LocalDate;
import java.util.UUID;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TourDto {

    private UUID tourId;
    private String tourName;
    private String tourStartAddress;
    private String tourEndAddress;
    private Double price;
    private Integer participantCount;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer starRating;
    private String phone;

}
