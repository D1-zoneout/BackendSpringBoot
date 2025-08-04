package com.sunbeam.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sunbeam.entities.Review;

@Repository
public interface ReviewDao extends JpaRepository<Review, Long> {
    List<Review> findByTurfId(Long turfId);
    Optional<Review> findById(Long id);
    List<Review> findAll();
    void delete(Review review);

}
