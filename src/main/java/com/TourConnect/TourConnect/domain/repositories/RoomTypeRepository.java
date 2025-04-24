package com.TourConnect.TourConnect.domain.repositories;

import com.TourConnect.TourConnect.domain.entities.RoomType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoomTypeRepository {

    RoomType save(RoomType roomType);
    Optional <RoomType> findById(UUID id);
    void deleteById(UUID id);
    void deleteAll();
    List<RoomType> findAll();
    List<RoomType> findByHotel_Id(UUID hotelId);
}
