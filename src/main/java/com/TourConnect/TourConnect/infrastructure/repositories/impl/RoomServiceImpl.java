package com.TourConnect.TourConnect.infrastructure.repositories.impl;

import com.TourConnect.TourConnect.domain.entities.Room;
import com.TourConnect.TourConnect.domain.repositories.RoomRepository;
import com.TourConnect.TourConnect.infrastructure.repositories.jpa.JpaRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class RoomServiceImpl implements RoomRepository {

    private final JpaRoomRepository roomRepository;

    public RoomServiceImpl(JpaRoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Room save(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Optional<Room> findById(UUID id) {
        return roomRepository.findById(id);
    }

    @Override
    public void deleteById(UUID id) {
        roomRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        roomRepository.deleteAll();
    }

    @Override
    public List<Room> findAll() {
        return roomRepository.findAll();
    }
}
