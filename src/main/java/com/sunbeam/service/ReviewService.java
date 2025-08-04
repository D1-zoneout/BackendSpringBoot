package com.sunbeam.service;

import com.sunbeam.dto.ReviewRequestDto;
import com.sunbeam.dto.ReviewResponseDto;
import java.util.List;

public interface ReviewService {
    void addReview(ReviewRequestDto dto, Long userId, Long turfId);
    ReviewResponseDto getReviewById(Long reviewId);
    List<ReviewResponseDto> getAllReviews();
    void deleteReviewById(Long reviewId);
}
