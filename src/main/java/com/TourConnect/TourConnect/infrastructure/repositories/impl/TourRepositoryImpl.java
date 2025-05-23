package com.TourConnect.TourConnect.infrastructure.repositories.impl;

import com.TourConnect.TourConnect.domain.entities.Tour;
import com.TourConnect.TourConnect.domain.repositories.TourRepository;
import com.TourConnect.TourConnect.infrastructure.repositories.jpa.JpaTourRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Component
public class TourRepositoryImpl implements TourRepository {

    private final JpaTourRepository tourRepository;

    public TourRepositoryImpl(JpaTourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    @Override
    public Tour save(Tour tour) {
        try {
            return tourRepository.save(tour);
        } catch (Exception e) {
            throw new RuntimeException("Error saving tour: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<Tour> findById(UUID id) {
         return tourRepository.findById(id);
    }

    @Override
    public void deleteById(UUID id) {
        tourRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        tourRepository.deleteAll();
    }

    @Override
    public List<Tour> findAll() {
        return tourRepository.findAll();
    }
}
