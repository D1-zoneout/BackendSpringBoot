package com.sunbeam.service;

import java.util.List;

import com.sunbeam.dto.ApiResponse;
import com.sunbeam.dto.BookingReqDTO;
import com.sunbeam.dto.BookingRespDTO;
import com.sunbeam.dto.UserBookingRespDTO;

public interface BookingService {
	
	List<BookingRespDTO> getAllBookings();
	ApiResponse createBooking(BookingReqDTO bookingDTO);
    BookingRespDTO getBookingById(Long id);
    List<BookingRespDTO> getBookingsByTurfId(Long turfId);
    boolean cancelBooking(Long bookingId);
	List<UserBookingRespDTO> getBookingsByUserId(Long userId);
	Object getAllCanceledBookings();

}
