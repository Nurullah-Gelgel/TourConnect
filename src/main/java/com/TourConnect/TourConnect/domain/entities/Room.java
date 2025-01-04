package com.TourConnect.TourConnect.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "room_type")
    private String roomType;

    @Column(name = "room_description")
    private String roomDescription;

    @Column(name = "room_capacity")
    private Integer roomCapacity;

    @Column(name = "room_price")
    private Double roomPrice;

    @Column(name = "room_status")
    private String roomStatus;

    @ManyToOne
    @JoinColumn(name = "hotelId", nullable = false)
    private Hotel hotel;

    @OneToMany(mappedBy = "room")
    private Set<Reservation> reservations;

}
