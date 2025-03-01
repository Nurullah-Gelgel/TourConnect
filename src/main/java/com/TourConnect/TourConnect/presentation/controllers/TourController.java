package com.TourConnect.TourConnect.presentation.controllers;

import com.TourConnect.TourConnect.application.dtos.TourDto;
import com.TourConnect.TourConnect.application.services.TourService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/tour")
public class TourController {

    private final TourService tourService;

    public TourController(TourService tourService) {
        this.tourService = tourService;
    }

    @GetMapping("/getAllTours")
    public ResponseEntity<List<TourDto>> getTours() {
        return ResponseEntity.ok(tourService.getAllTours());
    }

    @GetMapping("/getTourById")
    public ResponseEntity<TourDto> getTourById(UUID id) {
        return ResponseEntity.ok(tourService.getTourById(id));
    }

    @PostMapping("/createTour")
    public ResponseEntity<TourDto> createTour(@RequestBody TourDto tourDto) {
        return ResponseEntity.ok(tourService.createTour(tourDto));
    }

    @PutMapping("/updateTour")
    public ResponseEntity<TourDto> updateTour(UUID id, TourDto tourDto) {
        return ResponseEntity.ok(tourService.update(id, tourDto));
    }

    @DeleteMapping("/deleteTour")
    public ResponseEntity<Void> deleteTour(UUID id) {
        tourService.deleteTour(id);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/deleteAllTours")
    public ResponseEntity<Void> deleteAllTours() {
        tourService.deleteAllTours();
        return ResponseEntity.noContent().build();
    }

}
