package com.TourConnect.TourConnect.application.services;

import com.TourConnect.TourConnect.application.dtos.RoomTypeDto;
import com.TourConnect.TourConnect.application.mappers.RoomTypeMapper;
import com.TourConnect.TourConnect.domain.entities.RoomType;
import com.TourConnect.TourConnect.domain.repositories.RoomTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RoomTypeService {

    private final RoomTypeRepository roomTypeRepository;
    private final RoomTypeMapper roomTypeMapper;

    public RoomTypeService(RoomTypeRepository roomTypeRepository, RoomTypeMapper roomTypeMapper) {
        this.roomTypeRepository = roomTypeRepository;
        this.roomTypeMapper = roomTypeMapper;
    }

    public List<RoomTypeDto> getAllRoomTypes() {
        return roomTypeRepository.findAll().stream().map(roomTypeMapper::toDto).collect(Collectors.toList());
    }

    public RoomTypeDto getRoomTypeById(UUID id) {
        return roomTypeRepository.findById(id).map(roomTypeMapper::toDto).orElseThrow(() -> new RuntimeException("Room Type not found"));
    }


    public RoomTypeDto createRoomType(RoomTypeDto roomTypeDto) {
        roomTypeDto.setId(null);
        return roomTypeMapper.toDto(roomTypeRepository.save(roomTypeMapper.toEntity(roomTypeDto)));
    }

    public RoomTypeDto update(UUID id, RoomTypeDto roomTypeDto) {
        RoomType roomTypeToUpdate = roomTypeRepository.findById(id).orElseThrow(() -> new RuntimeException("Room Type not found"));
        roomTypeMapper.updateEntity(roomTypeDto, roomTypeToUpdate);
        return roomTypeMapper.toDto(roomTypeRepository.save(roomTypeToUpdate));
    }


    public void deleteRoomType(UUID id) {
        if (roomTypeRepository.findById(id).isPresent()) {
            roomTypeRepository.deleteById(id);
        } else {
            throw new RuntimeException("Room Type not found");
        }
    }

    public void deleteAllRoomTypes() {
        roomTypeRepository.deleteAll();
    }
}
