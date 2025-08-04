package com.sunbeam.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true)
public class BookingRespDTO {
	private LocalDate bookingDate;
    private boolean status;
    private String turfName;
    private LocalDate slotDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private String userName;
    private double price;
}
