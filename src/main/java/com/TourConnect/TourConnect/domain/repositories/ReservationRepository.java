package com.TourConnect.TourConnect.domain.repositories;

import com.TourConnect.TourConnect.domain.entities.Reservation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReservationRepository {

    Reservation save(Reservation reservation);
    Optional<Reservation> findByPnrCode(String pnrCode);

    Optional<Reservation> findById(UUID id);

    void deleteById(UUID id);
    void deleteAll();
    List<Reservation> findAll();
}
