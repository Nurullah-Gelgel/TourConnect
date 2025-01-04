package com.TourConnect.TourConnect.application.services;

import com.TourConnect.TourConnect.application.dtos.TourDto;
import com.TourConnect.TourConnect.application.mappers.TourMapper;
import com.TourConnect.TourConnect.domain.entities.Tour;
import com.TourConnect.TourConnect.domain.repositories.TourRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TourService {

    private final TourRepository tourRepository;
    private final TourMapper tourMapper;

    public TourService(TourRepository tourRepository, TourMapper tourMapper) {
        this.tourRepository = tourRepository;
        this.tourMapper = tourMapper;
    }

    public List<TourDto> getAllTours() {
        return tourRepository.findAll().stream().map(tourMapper::toDto).collect(Collectors.toList());
    }

    public TourDto getTourById(UUID id) {
        return tourRepository.findById(id).map(tourMapper::toDto).orElseThrow(() -> new RuntimeException("Tour not found"));
    }

    public TourDto createTour(TourDto tourDto) {

        Tour tour = tourMapper.toEntity(tourDto);
        Tour savedTour = tourRepository.save(tour);
        return tourMapper.toDto(savedTour);
    }

    public TourDto update(UUID id, TourDto tourDto) {

        Tour tourToUpdate = tourRepository.findById(id).orElseThrow(() -> new RuntimeException("Tour not found"));
        tourMapper.updateEntity(tourDto, tourToUpdate);
        return tourMapper.toDto(tourRepository.save(tourToUpdate));

    }

    public void deleteTour(UUID id) {

        if (tourRepository.findById(id).isPresent()) {
            tourRepository.deleteById(id);
        } else {
            throw new RuntimeException("Tour not found");
        }
    }

    public void deleteAllTours() {
        tourRepository.deleteAll();
    }
}
