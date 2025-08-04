package com.sunbeam.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunbeam.entities.Slot;

public interface SlotDao extends JpaRepository<Slot,Long>{
	
	List<Slot> findByTurfIdAndDate(Long turfId, LocalDate date);
	
	Optional<Slot> findByIdAndIsBookedTrue(Long id);

}
