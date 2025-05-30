package com.TourConnect.TourConnect.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hotel")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, name = "hotel_name")
    private String hotelName;

    @Column(name = "hotel_address")
    private String hotelAddress;

    @Column(name = "hotel_city")
    private String hotelCity;

    @Column(name = "advance_payment", nullable = false)
    private Double advancePayment;

    @Column(name = "hotel_state")
    private String district;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "hotel_photos",
            joinColumns = @JoinColumn(name = "hotel_id")
    )
    @Column(name = "photo_url", columnDefinition = "TEXT")
    private List<String> photoUrls = new ArrayList<>();

    @Column(name = "star_rating")
    private Integer starRating;

    @Column(name = "phone")
    private String phone;

    @Column(name = "api_url")
    private String apiUrl;

    @Column(name = "api_key")
    private String apiKey;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Room> rooms;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Reservation> reservations;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Review> reviews;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RoomType> roomTypes;

}