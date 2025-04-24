package com.TourConnect.TourConnect.application.dtos;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HotelDto {
    private UUID id;
    private String hotelName;
    private String hotelAddress;
    private String hotelCity;
    private Double advancePayment;
    private String district;
    private List<String> photoUrls = new ArrayList<>();
    private Integer starRating;
    private String phone;
    private String apiUrl;
    private String apiKey;
}