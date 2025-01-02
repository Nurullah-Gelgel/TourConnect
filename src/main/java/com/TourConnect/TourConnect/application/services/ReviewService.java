package com.TourConnect.TourConnect.application.services;

import com.TourConnect.TourConnect.application.dtos.ReviewDto;
import com.TourConnect.TourConnect.application.mappers.ReviewMapper;
import com.TourConnect.TourConnect.domain.entities.Review;
import com.TourConnect.TourConnect.domain.repositories.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    public List<ReviewDto> getAllReviews() {
        return reviewRepository.findAll().stream().map(reviewMapper::toDto).collect(java.util.stream.Collectors.toList());
    }

    public ReviewDto getReviewById(UUID id) {
        return reviewRepository.findById(id).map(reviewMapper::toDto).orElseThrow(() -> new RuntimeException("Review not found"));
    }

    public ReviewDto createReview(ReviewDto reviewDto) {
        Review review = reviewMapper.toEntity(reviewDto);
        Review savedReview = reviewRepository.save(review);
        return reviewMapper.toDto(savedReview);
    }

    public void deleteReview(UUID id) {
        if (reviewRepository.findById(id).isPresent()) {
            reviewRepository.deleteById(id);
        } else {
            throw new RuntimeException("Review not found");
        }
    }

    public void deleteAllReviews() {
        reviewRepository.deleteAll();
    }
}
