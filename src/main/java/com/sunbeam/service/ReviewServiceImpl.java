package com.sunbeam.service;

import com.sunbeam.dao.ReviewDao;
import com.sunbeam.dao.TurfDao;
import com.sunbeam.dao.UserDao;
import com.sunbeam.dto.ReviewRequestDto;
import com.sunbeam.dto.ReviewResponseDto;
import com.sunbeam.entities.Review;
import com.sunbeam.entities.Turf;
import com.sunbeam.entities.User;
import com.sunbeam.custom_exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewDao reviewDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private TurfDao turfDao;

    @Override
    public void addReview(ReviewRequestDto dto, Long userId, Long turfId) {
        Review review = new Review();
        review.setRating(dto.getRating());
        review.setReview(dto.getReview());

        User user = userDao.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Turf turf = turfDao.findById(turfId)
                .orElseThrow(() -> new ResourceNotFoundException("Turf not found"));

        review.setUser(user);
        review.setTurf(turf);

        reviewDao.save(review);
    }

    @Override
    public ReviewResponseDto getReviewById(Long reviewId) {
        Review review = reviewDao.findById(reviewId)
            .orElseThrow(() -> new ResourceNotFoundException("Review not found with ID: " + reviewId));
        return convertToDto(review);
    }


    @Override
    public List<ReviewResponseDto> getAllReviews() {
        return reviewDao.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public void deleteReviewById(Long reviewId) {
        Review review = reviewDao.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found"));
        reviewDao.delete(review);
    }

    private ReviewResponseDto convertToDto(Review review) {
        ReviewResponseDto dto = new ReviewResponseDto();
        dto.setId(review.getId());
        dto.setRating(review.getRating());
        dto.setReview(review.getReview());
        dto.setUserName(review.getUser().getName());
        dto.setTurfName(review.getTurf().getTurfName());
        return dto;
    }
}
