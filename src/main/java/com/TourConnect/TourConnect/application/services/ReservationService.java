package com.TourConnect.TourConnect.application.services;

import com.TourConnect.TourConnect.application.dtos.ReservationDto;
import com.TourConnect.TourConnect.application.dtos.ReservationResponseDto;
import com.TourConnect.TourConnect.application.mappers.ReservationMapper;
import com.TourConnect.TourConnect.domain.entities.Hotel;
import com.TourConnect.TourConnect.domain.entities.Reservation;
import com.TourConnect.TourConnect.domain.entities.Users;
import com.TourConnect.TourConnect.domain.repositories.HotelRepository;
import com.TourConnect.TourConnect.domain.repositories.ReservationRepository;

import com.TourConnect.TourConnect.domain.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.Random;


@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    private final HotelRepository hotelRepository;
    private final UserRepository usersRepository;

    public ReservationService(ReservationRepository reservationRepository, ReservationMapper reservationMapper, HotelRepository hotelRepository, UserRepository usersRepository) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
        this.hotelRepository = hotelRepository;
        this.usersRepository = usersRepository;
    }

    public List<ReservationDto> getAllReservations() {
        return reservationRepository.findAll().stream().map(reservationMapper::toDto).collect(Collectors.toList());
    }

    public ReservationDto getReservationById(UUID id) {
        return reservationRepository.findById(id)
                .map(reservation -> {
                    ReservationDto dto = reservationMapper.toDto(reservation);
                    dto.setAdvancePayment(reservation.getHotel().getAdvancePayment()); // Otelden advancePayment çekildi
                    return dto;
                })
                .orElseThrow(() -> new RuntimeException("Reservation not found"));    }

    @Transactional
    public ReservationDto createReservation(ReservationDto reservationDto) {
        reservationDto.setId(null); // Yeni kayıt olduğu için ID sıfırlanıyor

        Reservation reservation = reservationMapper.toEntity(reservationDto);

        String pnrCode = generatePNRCode();
        reservation.setPnrCode(pnrCode);

        if (reservationDto.getUserId() != null) {
            Users existingUser = usersRepository.findById(reservationDto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            reservation.setUsers(existingUser);
        } else {
            reservation.setUsers(null); // User ID null ise ilişkiyi boş bırak
        }

        Reservation savedReservation = reservationRepository.save(reservation);
        return reservationMapper.toDto(savedReservation);
    }

    public String generatePNRCode() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder pnr = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            pnr.append(characters.charAt(random.nextInt(characters.length())));
        }
        return pnr.toString();
    }

    public ReservationResponseDto getReservationDetailsByPnr(String pnrCode) {
        Reservation reservation = reservationRepository.findByPnrCode(pnrCode)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
        return reservationMapper.toResponse(reservation);
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


