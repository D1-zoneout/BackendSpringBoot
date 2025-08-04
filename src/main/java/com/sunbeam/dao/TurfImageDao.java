package com.sunbeam.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunbeam.entities.TurfImage;

public interface TurfImageDao extends JpaRepository<TurfImage, Long> {
    
	List<TurfImage> findByTurfId(Long turfId);
}
