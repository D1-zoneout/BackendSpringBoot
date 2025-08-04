package com.sunbeam.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter 
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "turf")
public class Turf extends BaseEntity{
	@Column(name = "turf_name")
	private String turfName;
	@Column(length = 100)
	private String description;
	@Column(name = "owner_name")
	private String ownerName;
	private String city;  
    private String state;
    private String pinCode;
    @Column(name = "is_active")
    private boolean isActive;
	@Column(name = "turf_location")
	private String turfLocation;
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private Sports sport;
	@OneToMany(mappedBy = "turf", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Slot> slots =  new ArrayList<>();
	@OneToMany(mappedBy = "turf", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Booking> bookings;
	@OneToMany(mappedBy = "turf", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Review> reviews;
	@OneToMany(mappedBy = "turf", cascade = CascadeType.ALL, orphanRemoval = true)//add as will be adding images as per the turf id..
	private List<TurfImage> images;

	
}
