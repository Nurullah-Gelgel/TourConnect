package com.TourConnect.TourConnect.presentation.controllers;

import com.TourConnect.TourConnect.application.dtos.TouristPlaceDto;
import com.TourConnect.TourConnect.application.services.TouristPlaceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/tour")
public class TouristPlaceController {

    private final TouristPlaceService touristPlaceService;

    public TouristPlaceController(TouristPlaceService touristPlaceService) {
        this.touristPlaceService = touristPlaceService;
    }

    @GetMapping("/getAllPlaces")
    public ResponseEntity<List<TouristPlaceDto>> getPlace() {
        return ResponseEntity.ok(touristPlaceService.getAllPlaces());
    }

    @GetMapping("/getPlaceById")
    public ResponseEntity<TouristPlaceDto> getPlaceById(UUID id) {
        return ResponseEntity.ok(touristPlaceService.getPlaceById(id));
    }

    @PostMapping("/createPlace")
    public ResponseEntity<TouristPlaceDto> createPlace(@RequestBody TouristPlaceDto tourDto) {
        return ResponseEntity.ok(touristPlaceService.createPlace(tourDto));
    }

    @PutMapping("/updatePlace")
    public ResponseEntity<TouristPlaceDto> updatePlace(UUID id, TouristPlaceDto tourDto) {
        return ResponseEntity.ok(touristPlaceService.update(id, tourDto));
    }

    @DeleteMapping("/deletePlace")
    public ResponseEntity<Void> deletePlace(UUID id) {
        touristPlaceService.deletePlace(id);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/deleteAllPlaces")
    public ResponseEntity<Void> deleteAllPlace() {
        touristPlaceService.deleteAllPlaces();
        return ResponseEntity.noContent().build();
    }

}
