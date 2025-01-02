package com.TourConnect.TourConnect.domain.repositories;

import com.TourConnect.TourConnect.domain.entities.Room;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoomRepository {

    Room save(Room room);

    Optional<Room> findById(UUID id);
    void deleteById(UUID id);
    void deleteAll();
    List<Room> findAll();
}
