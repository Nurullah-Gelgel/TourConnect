package com.TourConnect.TourConnect.application.services;

import com.TourConnect.TourConnect.application.dtos.HotelDto;
import com.TourConnect.TourConnect.application.mappers.HotelMapper;
import com.TourConnect.TourConnect.domain.entities.Hotel;
import com.TourConnect.TourConnect.domain.repositories.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;

    public HotelService(HotelRepository hotelRepository, HotelMapper hotelMapper) {
        this.hotelRepository = hotelRepository;
        this.hotelMapper = hotelMapper;
    }


    public List<HotelDto> getAllHotels() {
        return hotelRepository.findAll().stream().map(hotelMapper::toDto).collect(Collectors.toList());
    }

    public HotelDto getHotelById(UUID id) {
        return hotelRepository.findById(id).map(hotelMapper::toDto).orElseThrow(() -> new RuntimeException("Hotel not found"));
    }

    public HotelDto createHotel(HotelDto hotelDto) {
        return hotelMapper.toDto(hotelRepository.save(hotelMapper.toEntity(hotelDto)));
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


}
