package com.sunbeam.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunbeam.entities.Booking;
import com.sunbeam.entities.User;


public interface BookingDAO extends JpaRepository<Booking, Long>{
	List<Booking> findByStatusTrue();
	List<Booking> findByTurfId(Long turfId);
	List<Booking> findByUserId(Long userId);
	List<Booking> findByStatusFalse();
}
