package com.sunbeam.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.dto.ApiResponse;
import com.sunbeam.dto.BookingReqDTO;
import com.sunbeam.dto.BookingRespDTO;
import com.sunbeam.dto.UserBookingRespDTO;
import com.sunbeam.service.BookingService;

import lombok.AllArgsConstructor;

@RestController 
@RequestMapping("/booking")
@AllArgsConstructor
public class BookingController {
	private final BookingService bookingService;
	
	@GetMapping
	public ResponseEntity<?> ListBookings(){
    	List<BookingRespDTO> bookingList = bookingService.getAllBookings();
	    return ResponseEntity.ok(bookingList);		
	}
	
	@PostMapping
	public ResponseEntity<?> addBooking(@RequestBody BookingReqDTO dto) {
		 ApiResponse response = bookingService.createBooking(dto);
	        return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getBookingById(@PathVariable Long id) {
	    BookingRespDTO dto = bookingService.getBookingById(id);
	    return ResponseEntity.ok(dto);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<?> getBookingsByUserId(@PathVariable Long userId) {
	    List<UserBookingRespDTO> bookings = bookingService.getBookingsByUserId(userId);
	    return ResponseEntity.ok(bookings);
	}
	
	@GetMapping("/turf/{turfId}")
	public ResponseEntity<List<BookingRespDTO>> getBookingsByTurfId(@PathVariable Long turfId) {
	List<BookingRespDTO> bookings = bookingService.getBookingsByTurfId(turfId);
	return ResponseEntity.ok(bookings);
	}
	
	@GetMapping("/canceled")
    public ResponseEntity<?> getCanceledBookings() {
        return ResponseEntity.ok(bookingService.getAllCanceledBookings());
    }
	
	@DeleteMapping("/{bookingId}")
	public ResponseEntity<ApiResponse> cancelBooking(@PathVariable Long bookingId) {
	boolean deleted = bookingService.cancelBooking(bookingId);
	if (deleted)
	return ResponseEntity.ok(new ApiResponse("Booking cancelled and slot freed!"));
	else
	return ResponseEntity.status(HttpStatus.NOT_FOUND)
	.body(new ApiResponse("Booking not found!"));
	}
}
