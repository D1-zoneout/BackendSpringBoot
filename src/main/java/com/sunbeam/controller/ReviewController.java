package com.sunbeam.controller;

import com.sunbeam.dto.ApiResponse;
import com.sunbeam.dto.ReviewRequestDto;
import com.sunbeam.dto.ReviewResponseDto;
import com.sunbeam.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@CrossOrigin
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // POST: Add a review
    @PostMapping("/{userId}/{turfId}")
    public ResponseEntity<ApiResponse> addReview(
            @RequestBody ReviewRequestDto dto,
            @PathVariable Long userId,
            @PathVariable Long turfId) {
        reviewService.addReview(dto, userId, turfId);
        return ResponseEntity.ok(new ApiResponse("Review added successfully"));
    }

    /// GET: Fetch review by review ID
    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewResponseDto> getReviewById(@PathVariable Long reviewId) {
        ReviewResponseDto review = reviewService.getReviewById(reviewId);
        return ResponseEntity.ok(review);
    }


    // GET: Fetch all reviews (Admin access)
    @GetMapping("/admin/all")
    public ResponseEntity<List<ReviewResponseDto>> getAllReviews() {
        List<ReviewResponseDto> reviews = reviewService.getAllReviews();
        return ResponseEntity.ok(reviews);
    }

    // DELETE: Delete review by ID (Admin access)
    @DeleteMapping("/admin/delete/{reviewId}")
    public ResponseEntity<ApiResponse> deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReviewById(reviewId);
        return ResponseEntity.ok(new ApiResponse("Review deleted successfully"));
    }
}