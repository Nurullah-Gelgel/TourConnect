package com.TourConnect.TourConnect.application.services;


import com.TourConnect.TourConnect.application.dtos.TouristPlaceDto;
import com.TourConnect.TourConnect.application.mappers.TouristPlaceMapper;
import com.TourConnect.TourConnect.domain.entities.TouristPlace;
import com.TourConnect.TourConnect.domain.repositories.TouristPlaceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TouristPlaceService {

    private final TouristPlaceRepository touristPlaceRepository;
    private final TouristPlaceMapper touristPlaceMapper;

    public TouristPlaceService(TouristPlaceRepository tourRepository, TouristPlaceMapper touristPlaceMapper) {
        this.touristPlaceRepository = tourRepository;
        this.touristPlaceMapper = touristPlaceMapper;
    }

    public List<TouristPlaceDto> getAllPlaces() {
        return touristPlaceRepository.findAll().stream().map(touristPlaceMapper::toDto).collect(Collectors.toList());
    }

    public TouristPlaceDto getPlaceById(UUID id) {
        return touristPlaceRepository.findById(id).map(touristPlaceMapper::toDto).orElseThrow(() -> new RuntimeException("Tour not found"));
    }


    @Transactional
    public TouristPlaceDto createPlace(TouristPlaceDto tourDto) {
        // ID'yi null olarak ayarla
        tourDto.setId(null);

        TouristPlace tour = touristPlaceMapper.toEntity(tourDto);
        TouristPlace savedTour = touristPlaceRepository.save(tour);
        return touristPlaceMapper.toDto(savedTour);
    }

    public TouristPlaceDto update(UUID id, TouristPlaceDto tourDto) {

            TouristPlace tourToUpdate = touristPlaceRepository.findById(id).orElseThrow(() -> new RuntimeException("Tour not found"));
        touristPlaceMapper.updateEntity(tourDto, tourToUpdate);
        return touristPlaceMapper.toDto(touristPlaceRepository.save(tourToUpdate));

    }

    public void deletePlace(UUID id) {

        if (touristPlaceRepository.findById(id).isPresent()) {
            touristPlaceRepository.deleteById(id);
        } else {
            throw new RuntimeException("Tour not found");
        }
    }

    public void deleteAllPlaces() {
        touristPlaceRepository.deleteAll();
    }
}
