package com.sunbeam.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@Getter
@Setter
@ToString
public class BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;	
	 @CreationTimestamp
	 @Column(name = "created_date", updatable = false)
	 private LocalDateTime createdAt;
	 @UpdateTimestamp
	 @Column(name="updation_date")
	 private LocalDateTime updatedAt;
	
}
