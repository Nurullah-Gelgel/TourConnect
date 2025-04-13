package com.TourConnect.TourConnect.infrastructure.repositories.impl;

import com.TourConnect.TourConnect.domain.entities.Reservation;
import com.TourConnect.TourConnect.domain.repositories.ReservationRepository;
import com.TourConnect.TourConnect.infrastructure.repositories.jpa.JpaReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ReservationRepositoryImpl implements ReservationRepository {

    private final JpaReservationRepository reservationRepository;

    public ReservationRepositoryImpl(JpaReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public Optional<Reservation> findByPnrCode(String pnrCode) {
        return reservationRepository.findByPnrCode(pnrCode);
    }


    @Override
    public Optional<Reservation> findById(UUID id) {
        return reservationRepository.findById(id);
    }

    @Override
    public void deleteById(UUID id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        reservationRepository.deleteAll();
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }
}
