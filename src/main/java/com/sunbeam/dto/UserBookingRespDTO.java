package com.sunbeam.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserBookingRespDTO {
	    private LocalDate bookingDate;
	    private boolean status;
	    private String turfName;
	    private LocalDate slotDate;
	    private LocalTime startTime;
	    private LocalTime endTime;
	    private double price;
}
