package com.TourConnect.TourConnect.application.dtos;


import jakarta.persistence.*;
import lombok.*;


import java.time.LocalTime;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TouristPlaceDto {


    private UUID id;

    private String name; // Yer adı

    private String description; // Açıklama

    private String location; // Konum bilgisi

    private String image; // Fotoğraf URL'si

    private List<String> bestSeasons; // Hangi mevsimde en iyi ziyaret edilir

    private List<String> facilities; // WC, Kafe, Otopark vs.

    private LocalTime visitOpenTime;  // Açılış Saati

    private LocalTime visitCloseTime; // Kapanış Saati

    private List<String> visitDays; // Ziyaret Günleri

    private double entryFeeAdult;   // Tam Ücret

    private double entryFeeStudent; // Öğrenci Ücreti

    private double entryFeeMuseum;  // Müzekart Ücreti

    private List<String> transportationCars;

}