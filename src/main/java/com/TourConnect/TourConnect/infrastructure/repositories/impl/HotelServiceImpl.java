package com.TourConnect.TourConnect.infrastructure.repositories.impl;


import com.TourConnect.TourConnect.domain.entities.Hotel;
import com.TourConnect.TourConnect.domain.repositories.HotelRepository;
import com.TourConnect.TourConnect.infrastructure.repositories.jpa.JpaHotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelRepository {

    private final JpaHotelRepository jpaHotelRepository;

    @Override
    public Hotel save(Hotel hotel) {
        return jpaHotelRepository.save(hotel);
    }

    @Override
    public Optional<Hotel> findById(UUID id) {
        return jpaHotelRepository.findById(id);
    }

    @Override
    public List<Hotel> findAll() {
        return jpaHotelRepository.findAll();
    }

    @Override
    public void deleteById(UUID id) {
        jpaHotelRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        jpaHotelRepository.deleteAll();
    }
}
