package com.TourConnect.TourConnect.infrastructure.repositories.jpa;

import com.TourConnect.TourConnect.domain.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import org.springframework.stereotype.Repository;


@Repository
public interface JpaReservationRepository extends JpaRepository<Reservation, UUID> {
}
