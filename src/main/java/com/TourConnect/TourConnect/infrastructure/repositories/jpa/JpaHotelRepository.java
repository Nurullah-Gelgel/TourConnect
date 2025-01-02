package com.TourConnect.TourConnect.infrastructure.repositories.jpa;

import com.TourConnect.TourConnect.domain.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import org.springframework.stereotype.Repository;


@Repository
public interface JpaHotelRepository extends JpaRepository<Hotel, UUID> {
}
