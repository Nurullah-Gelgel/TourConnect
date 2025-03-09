package com.TourConnect.TourConnect.infrastructure.repositories.jpa;

import com.TourConnect.TourConnect.domain.entities.TouristPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface JpaTouristPlaceRepository extends JpaRepository<TouristPlace, UUID> {
}
