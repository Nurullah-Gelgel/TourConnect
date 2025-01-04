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
public class ReviewDto {

    private UUID id;
    private String comment;
    private Integer rating;
    private LocalDate date;
    private UUID userId;
    private UUID hotel_id;

}
