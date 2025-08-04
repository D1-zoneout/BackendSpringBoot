package com.sunbeam.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingReqDTO {
	private LocalDate bookingDate;
	private Long turfId;
    private Long slotId;
    private Long paymentId;
    private Long userId;
}
