package com.TourConnect.TourConnect.application.services;

import com.TourConnect.TourConnect.application.dtos.ReviewDto;
import com.TourConnect.TourConnect.application.mappers.ReviewMapper;
import com.TourConnect.TourConnect.domain.entities.Review;
import com.TourConnect.TourConnect.domain.repositories.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    public ReviewService(ReviewRepository reviewRepository, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
    }

    public List<ReviewDto> getAllReviews() {
        return reviewRepository.findAll().stream().map(reviewMapper::toDto).collect(java.util.stream.Collectors.toList());
    }

    public ReviewDto getReviewById(UUID id) {
        return reviewRepository.findById(id).map(reviewMapper::toDto).orElseThrow(() -> new RuntimeException("Review not found"));
    }

    @Transactional
    public ReviewDto createReview(ReviewDto reviewDto) {
        reviewDto.setId(null);
        Review review = reviewMapper.toEntity(reviewDto);
        Review savedReview = reviewRepository.save(review);
        return reviewMapper.toDto(savedReview);
    }

    public ReviewDto update(UUID id, ReviewDto reviewDto) {
        Review reviewToUpdate = reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Review not found"));
        reviewMapper.updateEntity(reviewDto, reviewToUpdate);
        return reviewMapper.toDto(reviewRepository.save(reviewToUpdate));
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
