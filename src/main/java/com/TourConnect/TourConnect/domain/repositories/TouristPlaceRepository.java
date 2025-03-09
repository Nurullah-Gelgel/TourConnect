package com.TourConnect.TourConnect.domain.repositories;


import com.TourConnect.TourConnect.domain.entities.TouristPlace;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TouristPlaceRepository {

    TouristPlace save(TouristPlace touristPlace);

    Optional<TouristPlace> findById(UUID id);
    void deleteById(UUID id);
    void deleteAll();
    List<TouristPlace> findAll();
}
