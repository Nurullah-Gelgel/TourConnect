package com.TourConnect.TourConnect.infrastructure.repositories.impl;

import com.TourConnect.TourConnect.domain.entities.RoomType;
import com.TourConnect.TourConnect.domain.repositories.RoomTypeRepository;
import com.TourConnect.TourConnect.infrastructure.repositories.jpa.JpaRoomTypeRepository;
import jakarta.persistence.Column;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class RoomTypeServiceImpl implements RoomTypeRepository {

    private final JpaRoomTypeRepository roomTypeRepository;

    public RoomTypeServiceImpl(JpaRoomTypeRepository roomTypeRepository) {
        this.roomTypeRepository = roomTypeRepository;
    }


    @Override
    public RoomType save(RoomType roomType) {
        try {
            return roomTypeRepository.save(roomType);
        } catch (Exception e) {
            throw new RuntimeException("Error saving room type: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<RoomType> findById(UUID id) {

    try {
            return roomTypeRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error finding room type by ID: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteById(UUID id) {
        try {
            roomTypeRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting room type by ID: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteAll() {
        try {
            roomTypeRepository.deleteAll();
        } catch (Exception e) {
            throw new RuntimeException("Error deleting all room types: " + e.getMessage(), e);
        }
    }

    @Override
    public List<RoomType> findAll() {
        try {
            return roomTypeRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error finding all room types: " + e.getMessage(), e);
        }
    }

    @Override
    public List<RoomType> findByHotel_Id(UUID hotelId) {
        try {
            return roomTypeRepository.findByHotel_Id(hotelId);
        } catch (Exception e) {
            throw new RuntimeException("Error finding room types by hotel ID: " + e.getMessage(), e);
        }
    }
}
