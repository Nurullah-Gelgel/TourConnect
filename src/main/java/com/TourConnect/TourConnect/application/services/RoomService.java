package com.TourConnect.TourConnect.application.services;

import com.TourConnect.TourConnect.application.dtos.RoomDto;
import com.TourConnect.TourConnect.application.dtos.TourDto;
import com.TourConnect.TourConnect.application.mappers.RoomMapper;
import com.TourConnect.TourConnect.domain.entities.Room;
import com.TourConnect.TourConnect.domain.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    public RoomService(RoomRepository roomRepository, RoomMapper roomMapper) {
        this.roomRepository = roomRepository;
        this.roomMapper = roomMapper;
    }

    public List<RoomDto> getAllRooms() {

        return roomRepository.findAll().stream().map(roomMapper::toDto).collect(Collectors.toList());
    }

    public RoomDto getRoomById(UUID id) {

        return roomRepository.findById(id).map(roomMapper::toDto).orElseThrow(() -> new RuntimeException("Room not found"));
    }

    public RoomDto update(UUID id, RoomDto roomDto) {

        Room roomToUpdate = roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found"));
        roomMapper.updateEntity(roomDto, roomToUpdate);
        return roomMapper.toDto(roomRepository.save(roomToUpdate));

    }


    public RoomDto createRoom(RoomDto roomDto) {

            Room room = roomMapper.toEntity(roomDto);
            Room savedRoom = roomRepository.save(room);
            return roomMapper.toDto(savedRoom);
    }

    public void deleteRoom(UUID id) {

        if (roomRepository.findById(id).isPresent()) {
            roomRepository.deleteById(id);
        } else {
            throw new RuntimeException("Room not found");
        }
    }

    public void deleteAllRooms() {
        roomRepository.deleteAll();
    }
}
