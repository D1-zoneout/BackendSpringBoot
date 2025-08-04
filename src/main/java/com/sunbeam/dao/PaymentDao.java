package com.sunbeam.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sunbeam.entities.Payment;

public interface PaymentDao extends JpaRepository<Payment, Long> {
   
	List<Payment> findByTurfId(Long turfId);
}