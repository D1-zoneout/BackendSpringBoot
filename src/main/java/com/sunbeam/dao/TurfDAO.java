package com.sunbeam.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunbeam.entities.Turf;

public interface TurfDao extends JpaRepository<Turf, Long> {
    List<Turf> findByIsActive(boolean isActive);
}
