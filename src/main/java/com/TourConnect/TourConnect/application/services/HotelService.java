package com.TourConnect.TourConnect.application.services;

import com.TourConnect.TourConnect.application.dtos.HotelDto;
import com.TourConnect.TourConnect.application.dtos.RoomTypeDto;
import com.TourConnect.TourConnect.application.mappers.HotelMapper;
import com.TourConnect.TourConnect.application.mappers.RoomTypeMapper;
import com.TourConnect.TourConnect.domain.entities.Hotel;
import com.TourConnect.TourConnect.domain.entities.RoomType;
import com.TourConnect.TourConnect.domain.repositories.HotelRepository;
import com.TourConnect.TourConnect.domain.repositories.RoomTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;
    private final RoomTypeRepository roomTypeRepository;
    private final RoomTypeMapper roomTypeMapper;

    public HotelService(HotelRepository hotelRepository, HotelMapper hotelMapper, RoomTypeRepository roomTypeRepository, RoomTypeMapper roomTypeMapper) {
        this.hotelRepository = hotelRepository;
        this.hotelMapper = hotelMapper;
        this.roomTypeRepository = roomTypeRepository;
        this.roomTypeMapper = roomTypeMapper;
    }


    public List<HotelDto> getAllHotels() {
        return hotelRepository.findAll().stream().map(hotelMapper::toDto).collect(Collectors.toList());
    }

    public HotelDto getHotelById(UUID id) {
        return hotelRepository.findById(id).map(hotelMapper::toDto).orElseThrow(() -> new RuntimeException("Hotel not found"));
    }
    @Transactional
    public HotelDto createHotel(HotelDto hotelDto) {
        hotelDto.setId(null);
        return hotelMapper.toDto(hotelRepository.save(hotelMapper.toEntity(hotelDto)));
    }

    public Double getHotelPrice(UUID id) {
        return hotelRepository.findById(id).map(Hotel::getAdvancePayment).orElseThrow(() -> new RuntimeException("Hotel not found"));
    }

    public HotelDto update(UUID id, HotelDto hotelDto) {
        Hotel hotelToUpdate = hotelRepository.findById(id).orElseThrow(() -> new RuntimeException("Hotel not found"));
        hotelMapper.updateEntity(hotelDto, hotelToUpdate);
        return hotelMapper.toDto(hotelRepository.save(hotelToUpdate));
    }

    public void deleteHotel(UUID id) {
        if (hotelRepository.findById(id).isPresent()) {
            hotelRepository.deleteById(id);
        } else {
            throw new RuntimeException("Hotel not found");
        }
    }

    public void deleteAllHotels() {
        hotelRepository.deleteAll();
    }

    public List<RoomTypeDto> getRoomTypesByHotelId(UUID hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new EntityNotFoundException("Hotel not found"));

        List<RoomType> roomTypes = roomTypeRepository.findByHotel_Id(hotelId);
        return roomTypes.stream()
                .map(roomTypeMapper::toDto)
                .collect(Collectors.toList());
    }

}
