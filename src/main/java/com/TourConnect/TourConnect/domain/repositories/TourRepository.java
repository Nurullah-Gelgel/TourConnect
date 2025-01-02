package com.TourConnect.TourConnect.domain.repositories;

import com.TourConnect.TourConnect.domain.entities.Tour;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TourRepository {

    Tour save(Tour tour);

    Optional<Tour> findById(UUID id);
    void deleteById(UUID id);
    void deleteAll();
    List<Tour> findAll();
}
