package com.sunbeam.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunbeam.entities.Turf;

public interface TurfDAO extends JpaRepository<Turf, Long> {

}
