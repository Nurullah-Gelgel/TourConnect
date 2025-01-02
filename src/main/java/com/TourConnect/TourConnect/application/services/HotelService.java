package com.TourConnect.TourConnect.application.services;

import com.TourConnect.TourConnect.application.dtos.HotelDto;
import com.TourConnect.TourConnect.application.mappers.HotelMapper;
import com.TourConnect.TourConnect.domain.repositories.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class HotelService {

    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;

    public List<HotelDto> getAllHotels() {
        return hotelRepository.findAll().stream().map(hotelMapper::toDto).collect(Collectors.toList());
    }

    public HotelDto getHotelById(UUID id) {
        return hotelRepository.findById(id).map(hotelMapper::toDto).orElseThrow(() -> new RuntimeException("Hotel not found"));
    }

    public HotelDto createHotel(HotelDto hotelDto) {
        return hotelMapper.toDto(hotelRepository.save(hotelMapper.toEntity(hotelDto)));
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


}
