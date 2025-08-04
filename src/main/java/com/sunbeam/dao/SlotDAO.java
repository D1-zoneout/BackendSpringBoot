package com.sunbeam.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunbeam.entities.Slot;

public interface SlotDAO extends JpaRepository<Slot, Long> {

}
