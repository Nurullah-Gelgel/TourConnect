package com.TourConnect.TourConnect.domain.repositories;

import com.TourConnect.TourConnect.domain.entities.Review;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReviewRepository {

    Review save(Review review);
    Optional<Review> findById(UUID id);
    void deleteById(UUID id);
    void deleteAll();
    List<Review> findAll();

}
