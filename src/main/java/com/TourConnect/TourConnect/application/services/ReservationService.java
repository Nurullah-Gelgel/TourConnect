package com.TourConnect.TourConnect.application.services;

import com.TourConnect.TourConnect.application.dtos.ReservationDto;
import com.TourConnect.TourConnect.application.mappers.ReservationMapper;
import com.TourConnect.TourConnect.domain.repositories.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    public List<ReservationDto> getAllReservations() {
        return reservationRepository.findAll().stream().map(reservationMapper::toDto).collect(Collectors.toList());
    }

    public ReservationDto getReservationById(UUID id) {
        return reservationRepository.findById(id).map(reservationMapper::toDto).orElseThrow(() -> new RuntimeException("Reservation not found"));
    }

    public ReservationDto createReservation(ReservationDto reservationDto) {
        return reservationMapper.toDto(reservationRepository.save(reservationMapper.toEntity(reservationDto)));
    }

    public void deleteReservation(UUID id) {
        if (reservationRepository.findById(id).isPresent()) {
            reservationRepository.deleteById(id);
        } else {
            throw new RuntimeException("Reservation not found");
        }
    }

    public void deleteAllReservations() {
        reservationRepository.deleteAll();
    }

}


