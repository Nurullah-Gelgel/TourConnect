package com.TourConnect.TourConnect.presentation.controllers;

import com.TourConnect.TourConnect.application.dtos.ReservationDto;
import com.TourConnect.TourConnect.application.services.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/getAllReservations")
    public ResponseEntity<List<ReservationDto>> getReservations() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    @GetMapping("/getReservationById")
    public ResponseEntity<ReservationDto> getReservationById(UUID id) {
        return ResponseEntity.ok(reservationService.getReservationById(id));
    }

    @PostMapping("/createReservation")
    public ResponseEntity<ReservationDto> createReservation(ReservationDto reservationDto) {
        return ResponseEntity.ok(reservationService.createReservation(reservationDto));
    }

    @PutMapping("/updateReservation")
    public ResponseEntity<ReservationDto> updateReservation(UUID id, ReservationDto reservationDto) {
        return ResponseEntity.ok(reservationService.update(id, reservationDto));
    }

    @DeleteMapping("/deleteReservation")
    public ResponseEntity<Void> deleteReservation(UUID id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteAllReservations")
    public ResponseEntity<Void> deleteAllReservations() {
        reservationService.deleteAllReservations();
        return ResponseEntity.noContent().build();
    }




}
