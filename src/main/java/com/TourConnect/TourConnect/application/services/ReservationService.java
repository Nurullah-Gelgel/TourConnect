package com.TourConnect.TourConnect.application.services;

import com.TourConnect.TourConnect.application.dtos.ReservationDto;
import com.TourConnect.TourConnect.application.mappers.ReservationMapper;
import com.TourConnect.TourConnect.domain.entities.Reservation;
import com.TourConnect.TourConnect.domain.repositories.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    public ReservationService(ReservationRepository reservationRepository, ReservationMapper reservationMapper) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
    }

    public List<ReservationDto> getAllReservations() {
        return reservationRepository.findAll().stream().map(reservationMapper::toDto).collect(Collectors.toList());
    }

    public ReservationDto getReservationById(UUID id) {
        return reservationRepository.findById(id).map(reservationMapper::toDto).orElseThrow(() -> new RuntimeException("Reservation not found"));
    }

    @Transactional
    public ReservationDto createReservation(ReservationDto reservationDto) {    
        reservationDto.setId(null);
        return reservationMapper.toDto(reservationRepository.save(reservationMapper.toEntity(reservationDto)));
    }

    public ReservationDto update(UUID id, ReservationDto reservationDto) {
        Reservation reservationToUpdate = reservationRepository.findById(id).orElseThrow(()->new RuntimeException("Reservation not found"));
        reservationMapper.updateEntity(reservationDto, reservationToUpdate);
        return reservationMapper.toDto(reservationRepository.save(reservationToUpdate));
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


