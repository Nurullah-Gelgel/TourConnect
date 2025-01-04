package com.TourConnect.TourConnect.presentation.controllers;

import com.TourConnect.TourConnect.application.dtos.RoomDto;
import com.TourConnect.TourConnect.application.services.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/room")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/getAllRooms")
    public ResponseEntity<List<RoomDto>> getRooms() {
        return ResponseEntity.ok(roomService.getAllRooms());
    }

    @GetMapping("/getRoomById")
    public ResponseEntity<RoomDto> getRoomById(UUID id) {
        return ResponseEntity.ok(roomService.getRoomById(id));
    }

    @PostMapping("/createRoom")
    public ResponseEntity<RoomDto> createRoom(RoomDto roomDto) {
        return ResponseEntity.ok(roomService.createRoom(roomDto));
    }

    @PutMapping("/updateRoom")
    public ResponseEntity<RoomDto> updateRoom(UUID id, RoomDto roomDto) {
        return ResponseEntity.ok(roomService.update(id, roomDto));
    }

    @DeleteMapping("/deleteRoom")
    public ResponseEntity<Void> deleteRoom(UUID id) {
        roomService.deleteRoom(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteAllRooms")
    public ResponseEntity<Void> deleteAllRooms() {
        roomService.deleteAllRooms();
        return ResponseEntity.noContent().build();
    }
}
