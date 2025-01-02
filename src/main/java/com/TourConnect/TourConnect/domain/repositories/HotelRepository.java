package com.TourConnect.TourConnect.domain.repositories;

import com.TourConnect.TourConnect.domain.entities.Hotel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HotelRepository {

    Hotel save(Hotel hotel);
    Optional<Hotel> findById(UUID id);
    List<Hotel> findAll();
    void deleteById(UUID id);
    void deleteAll();

}
