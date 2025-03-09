package com.TourConnect.TourConnect.infrastructure.repositories.impl;


import com.TourConnect.TourConnect.domain.entities.TouristPlace;
import com.TourConnect.TourConnect.domain.repositories.TouristPlaceRepository;
import com.TourConnect.TourConnect.infrastructure.repositories.jpa.JpaTouristPlaceRepository;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.UUID;
import java.util.Optional;


@Component
public class TouristPlaceRepositoryImpl implements TouristPlaceRepository {

    private final JpaTouristPlaceRepository touristPlaceRepository;

    public TouristPlaceRepositoryImpl(JpaTouristPlaceRepository touristPlaceRepository) {
        this.touristPlaceRepository = touristPlaceRepository;
    }

    @Override
    public TouristPlace save(TouristPlace tour) {
        try {
            return touristPlaceRepository.save(tour);
        } catch (Exception e) {
            throw new RuntimeException("Error saving tour: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<TouristPlace> findById(UUID id) {
        return touristPlaceRepository.findById(id);
    }

    @Override
    public void deleteById(UUID id) {
        touristPlaceRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        touristPlaceRepository.deleteAll();
    }

    @Override
    public List<TouristPlace> findAll() {
        return touristPlaceRepository.findAll();
    }
}
