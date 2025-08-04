package com.sunbeam.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "slots")
public class Slot extends BaseEntity{
	private LocalDate date;
	private LocalTime startTime;
    private LocalTime endTime;
    private boolean isBooked;
    private double price;
    private Long bookedBy; // FK to User
    @ManyToOne
    @JoinColumn(name = "turf_id")
    private Turf turf;    
    @OneToMany(mappedBy = "slot", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Booking> bookings;
    
}
