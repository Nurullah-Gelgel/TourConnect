package com.TourConnect.TourConnect.presentation.controllers;


import com.TourConnect.TourConnect.application.dtos.ReviewDto;
import com.TourConnect.TourConnect.application.services.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/review")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("public/getAllReviews")
    public ResponseEntity<List<ReviewDto>> getReviews() {
        return ResponseEntity.ok(reviewService.getAllReviews());
    }

    @GetMapping("public/getReviewById")
    public ResponseEntity<ReviewDto> getReviewById(UUID id) {
        return ResponseEntity.ok(reviewService.getReviewById(id));
    }

    @PostMapping("public/createReview")
    public ResponseEntity<ReviewDto> createReview(@RequestBody ReviewDto reviewDto) {
        return ResponseEntity.ok(reviewService.createReview(reviewDto));
    }

    @PutMapping("public/updateReview")
    public ResponseEntity<ReviewDto> updateReview(@RequestBody ReviewDto reviewDto) {
        return ResponseEntity.ok(reviewService.update(reviewDto.getId(), reviewDto));
    }

    @DeleteMapping("public/deleteReview")
    public ResponseEntity<Void> deleteReview(UUID id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteAllReviews")
    public ResponseEntity<Void> deleteAllReviews() {
        reviewService.deleteAllReviews();
        return ResponseEntity.noContent().build();
    }

}
