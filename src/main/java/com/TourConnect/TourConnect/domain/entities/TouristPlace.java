package com.TourConnect.TourConnect.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TouristPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, name = "name")
    private String name; // Yer adı

    @Column(nullable = false, name = "description")
    private String description; // Açıklama

    @Column(nullable = false, name = "location")
    private String location; // Konum bilgisi

    @Column(nullable = false, name = "image")
    private String image; // Fotoğraf URL'si

    @ElementCollection
    @Column(nullable = false, name = "best_seasons")
    private List<String> bestSeasons; // Hangi mevsimde en iyi ziyaret edilir

    @ElementCollection
    @Column(nullable = false, name = "facilities")
    private List<String> facilities; // WC, Kafe, Otopark vs.

    @Column(nullable = false, name = "visit_open_time")
    private LocalDate visitOpenTime;  // Açılış Saati

    @Column(nullable = false, name = "visit_close_time")
    private LocalDate visitCloseTime; // Kapanış Saati

    @Column(nullable = false, name = "visit_days")
    private List<String> visitDays; // Ziyaret Günleri

    @Column(nullable = false, name = "entry_fee_adult")
    private double entryFeeAdult;   // Tam Ücret

    @Column(nullable = false, name = "entry_fee_student")
    private double entryFeeStudent; // Öğrenci Ücreti

    @Column(nullable = false, name = "entry_fee_museum")
    private double entryFeeMuseum;  // Müzekart Ücreti

    @ElementCollection
    @Column(nullable = false, name = "transportation_cars")
    private List<String> transportationCars;

}