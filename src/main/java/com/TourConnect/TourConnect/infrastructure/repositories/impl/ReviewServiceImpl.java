package com.TourConnect.TourConnect.infrastructure.repositories.impl;

import com.TourConnect.TourConnect.domain.entities.Review;
import com.TourConnect.TourConnect.domain.repositories.ReviewRepository;
import com.TourConnect.TourConnect.infrastructure.repositories.jpa.JpaReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewRepository {

    private final JpaReviewRepository reviewRepository;
    @Override
    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Optional<Review> findById(UUID id) {
        return reviewRepository.findById(id);
    }

    @Override
    public void deleteById(UUID id) {
        reviewRepository.deleteById(id);    }

    @Override
    public void deleteAll() {
        reviewRepository.deleteAll();
    }

    @Override
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }
}
