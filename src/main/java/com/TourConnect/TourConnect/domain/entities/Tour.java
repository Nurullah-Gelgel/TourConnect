package com.TourConnect.TourConnect.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Tour")
public class Tour {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false,name = "tour_name")
    private String tourName;

    @Column(name = "tour_start_address")
    private String tourStartAddress;

    @Column(name = "tour_end_address")
    private String tourEndAddress;

    @Column(name = "price")
    private Double price;

    @Column(name = "participant_count")
    private Integer participantCount;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "star_rating")
    private Integer starRating;

    @Column(name = "phone")
    private String phone;




}
