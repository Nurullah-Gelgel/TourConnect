package com.TourConnect.TourConnect.presentation.controllers;

import com.TourConnect.TourConnect.application.dtos.HotelDto;
import com.TourConnect.TourConnect.application.dtos.RoomTypeDto;
import com.TourConnect.TourConnect.application.services.HotelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/hotel")
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("public/getAllHotels")
    public ResponseEntity<List<HotelDto>> getHotels() {
        return ResponseEntity.ok(hotelService.getAllHotels());
    }

    @GetMapping("public/getHotelById")
    public ResponseEntity<HotelDto> getHotelById(@RequestParam UUID id) {
        return ResponseEntity.ok(hotelService.getHotelById(id));
    }

    @GetMapping("public/{hotelId}/room-types")
    public ResponseEntity<List<RoomTypeDto>> getRoomTypes(@PathVariable UUID hotelId) {
        List<RoomTypeDto> responses = hotelService.getRoomTypesByHotelId(hotelId);
        return ResponseEntity.ok(responses);
    }

    @PostMapping("/createHotel")
    public ResponseEntity<HotelDto> createHotel(@RequestBody HotelDto hotelDto) {
        return ResponseEntity.ok(hotelService.createHotel(hotelDto));
    }

    @PutMapping("/updateHotel")
    public ResponseEntity<HotelDto> updateHotel(@RequestParam UUID id, @RequestBody HotelDto hotelDto) {
        return ResponseEntity.ok(hotelService.update(id, hotelDto));
    }

    @DeleteMapping("/deleteHotel")
    public ResponseEntity<Void> deleteHotel(@RequestParam UUID id) {
        hotelService.deleteHotel(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteAllHotels")
    public ResponseEntity<Void> deleteAllHotels() {
        hotelService.deleteAllHotels();
        return ResponseEntity.noContent().build();
    }

}
