package com.sunbeam.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunbeam.custom_exceptions.ResourceNotFoundException;
import com.sunbeam.dao.BookingDAO;
import com.sunbeam.dao.SlotDAO;
import com.sunbeam.dao.TurfDAO;
import com.sunbeam.dao.UserDAO;
import com.sunbeam.dto.ApiResponse;
import com.sunbeam.dto.BookingReqDTO;
import com.sunbeam.dto.BookingRespDTO;
import com.sunbeam.dto.UserBookingRespDTO;
import com.sunbeam.entities.Booking;
import com.sunbeam.entities.Slot;
import com.sunbeam.entities.Turf;
import com.sunbeam.entities.User;

import lombok.AllArgsConstructor;

@Service 
@Transactional
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {
	private final BookingDAO bookingDAO;
	private final SlotDAO slotDAO;
	private final UserDAO userDAO;
	private final TurfDAO turfDAO;
	private final ModelMapper modelMapper;
	private EmailService emailService;

	@Override
	public List<BookingRespDTO> getAllBookings() {
		 List<Booking> bookings = bookingDAO.findAll();
		
		 
		return bookings.stream().map(booking -> {
            BookingRespDTO dto = new BookingRespDTO();
            dto.setBookingDate(booking.getBookingDate());
            dto.setTurfName(booking.getTurf().getTurfName());
            dto.setSlotDate(booking.getSlot().getDate());
            dto.setStartTime(booking.getSlot().getStartTime());
            dto.setEndTime(booking.getSlot().getEndTime());
            dto.setUserName(booking.getUser().getName());
            dto.setPrice(booking.getSlot().getPrice());
            return dto;
        }).collect(Collectors.toList());
	}

	@Override
	public ApiResponse createBooking(BookingReqDTO bookingDTO) {
		  Booking booking = new Booking();
		    booking.setBookingDate(bookingDTO.getBookingDate());
		    booking.setStatus(true);

		    Turf turf = turfDAO.findById(bookingDTO.getTurfId())
		        .orElseThrow(() -> new RuntimeException("Turf not found"));

		    Slot slot = slotDAO.findById(bookingDTO.getSlotId())
		    		 .orElseThrow(() -> new RuntimeException("Slot not found"));
		    // Check if slot is available
		    if (slot.isBooked()) {
		        throw new RuntimeException("Slot is already booked. Please select another slot.");
		    }

		    User user = userDAO.findById(bookingDTO.getUserId())
		        .orElseThrow(() -> new RuntimeException("User not found"));

		    booking.setTurf(turf);
		    booking.setSlot(slot);
		    booking.setUser(user);
		    bookingDAO.save(booking);
		    // Mark slot as unavailable
		    slot.setBooked(true);
		    slotDAO.save(slot);
		    
		    // Send Email
		    String subject = "Booking Confirmation";
		    String body = "Hi " + user.getName() + ",\n\nYour booking at " + turf.getTurfName() +
		                  " on " + slot.getDate() + " from " + slot.getStartTime() + " to " + slot.getEndTime() +
		                  " has been successfully confirmed.\n\nThank you!";
		    emailService.sendBookingConfirmation(user.getEmail(), subject, body);

		    //BookingRespDTO respDTO = modelMapper.map(saved, BookingRespDTO.class);

		    return new ApiResponse("Booking created successfully");
	}

	@Override
	public BookingRespDTO getBookingById(Long id) {
		Booking booking = bookingDAO.findById(id)
		        .orElseThrow(() -> new ResourceNotFoundException("Booking not found with ID: " + id));

		    BookingRespDTO dto = new BookingRespDTO();
		    dto.setBookingDate(booking.getBookingDate());
		    dto.setTurfName(booking.getTurf().getTurfName());
		    dto.setSlotDate(booking.getSlot().getDate());
		    dto.setStartTime(booking.getSlot().getStartTime());
		    dto.setEndTime(booking.getSlot().getEndTime());
		    dto.setUserName(booking.getUser().getName());
		    dto.setPrice(booking.getSlot().getPrice());
		    return dto;
	}
	
	@Override
	public List<UserBookingRespDTO> getBookingsByUserId(Long userId) {
		 List<Booking> bookings = bookingDAO.findByUserId(userId);
		 return bookings.stream().map(booking -> {
	            UserBookingRespDTO dto = new UserBookingRespDTO();
	            dto.setBookingDate(booking.getBookingDate());
	            dto.setStatus(booking.isStatus());

	            if (booking.getTurf() != null) {
	                dto.setTurfName(booking.getTurf().getTurfName());
	            }

	            if (booking.getSlot() != null) {
	                dto.setSlotDate(booking.getSlot().getDate());
	                dto.setStartTime(booking.getSlot().getStartTime());
	                dto.setEndTime(booking.getSlot().getEndTime());
	                dto.setPrice(booking.getSlot().getPrice());
	            }

	            return dto;
	        }).collect(Collectors.toList());
	}

	@Override
	public List<BookingRespDTO> getBookingsByTurfId(Long turfId) {
		List<Booking> bookings = bookingDAO.findByTurfId(turfId);
		return bookings.stream()
			    .map(booking -> {
			        BookingRespDTO dto = new BookingRespDTO();
			        dto.setBookingDate(booking.getBookingDate());
			        dto.setTurfName(booking.getTurf().getTurfName());
			        dto.setSlotDate(booking.getSlot().getDate());
			        dto.setStartTime(booking.getSlot().getStartTime());
			        dto.setEndTime(booking.getSlot().getEndTime());
			        dto.setUserName(booking.getUser().getName());
			        dto.setPrice(booking.getSlot().getPrice());
			        return dto;
			    }).toList();
	}
	
	public List<BookingRespDTO> getAllCanceledBookings() {
		
        List<Booking> canceled = bookingDAO.findByStatusFalse();
		 return canceled.stream().map(booking -> {
	            BookingRespDTO dto = modelMapper.map(booking, BookingRespDTO.class);
	            // If nested fields (turfName, slotDate, etc.) need explicit mapping:
	            dto.setTurfName(booking.getTurf().getTurfName());
	            dto.setSlotDate(booking.getSlot().getDate());
	            dto.setStartTime(booking.getSlot().getStartTime());
	            dto.setEndTime(booking.getSlot().getEndTime());
	            dto.setUserName(booking.getUser().getName());
	            dto.setPrice(booking.getSlot().getPrice());
	            return dto;
	        }).collect(Collectors.toList());
    }

	@Override
	public boolean cancelBooking(Long bookingId) {
		Optional<Booking> optBooking = bookingDAO.findById(bookingId);		
		if (optBooking.isPresent()) {
			Booking booking = optBooking.get();
			// Set booking status to false (soft delete)
			booking.setStatus(false);
			 // Free the slot
		    Slot slot = booking.getSlot();
		    slot.setBooked(false);
		    // Save updates
		    bookingDAO.save(booking);
		    slotDAO.save(slot);
		    return true;
		}
  		return false;
	}	
}
