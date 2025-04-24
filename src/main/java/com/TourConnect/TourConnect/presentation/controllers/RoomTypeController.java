package com.TourConnect.TourConnect.presentation.controllers;

import com.TourConnect.TourConnect.application.dtos.ContactDto;
import com.TourConnect.TourConnect.application.dtos.RoomTypeDto;
import com.TourConnect.TourConnect.application.services.RoomTypeService;
import com.TourConnect.TourConnect.domain.entities.RoomType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/roomType")
public class RoomTypeController {

    private final RoomTypeService roomTypeService;

    public RoomTypeController(RoomTypeService roomTypeService) {
        this.roomTypeService = roomTypeService;
    }



     @GetMapping("/getAllRoomTypes")
    public ResponseEntity<List<RoomTypeDto>> getAllRoomTypes() {
         return ResponseEntity.ok(roomTypeService.getAllRoomTypes());
     }

    @GetMapping("/getRoomTypeById")
    public ResponseEntity<RoomTypeDto> getRoomTypeById(@RequestParam UUID id) {
        return ResponseEntity.ok(roomTypeService.getRoomTypeById(id));
    }

    @PostMapping("/createRoomType")
    public ResponseEntity<RoomTypeDto> createRoomType(@RequestBody RoomTypeDto roomTypeDto) {
        return ResponseEntity.ok(roomTypeService.createRoomType(roomTypeDto));
    }

    @PutMapping("/updateRoomType")
    public ResponseEntity<RoomTypeDto> updateRoomType(@RequestParam UUID id, @RequestBody RoomTypeDto roomTypeDto) {
        return ResponseEntity.ok(roomTypeService.update(id, roomTypeDto));
    }

    @DeleteMapping("/deleteRoomType")
    public ResponseEntity<Void> deleteRoomType(@RequestParam UUID id) {
        roomTypeService.deleteRoomType(id);
        return ResponseEntity.noContent().build();
    }



}
