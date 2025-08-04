package com.sunbeam.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunbeam.custom_exceptions.ResourceNotFoundException;
import com.sunbeam.dao.SlotDao;
import com.sunbeam.dto.BookedSlotDTO;
import com.sunbeam.dto.SlotRespDTO;
import com.sunbeam.entities.Slot;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Transactional

public class SlotServiceImpl implements SlotService{
	private final SlotDao slotDao;
	private final ModelMapper mapper;
	
	//get all available slots
	@Override
	public List<SlotRespDTO> getSlotsByTurfAndDate(Long turfId, LocalDate date) {
		// TODO Auto-generated method stub
		//fetch already booked slots from database
		List<Slot> bookedSlots =slotDao.findByTurfIdAndDate(turfId, date);
		
		//store booked times in a set for fast lookup
		Set<LocalTime> bookedStartTimes = new HashSet<>();
		for(Slot slot : bookedSlots)
		{
			bookedStartTimes.add(slot.getStartTime());
		}
		
		//GENERATE all one hour slots
		List<SlotRespDTO> allSlots = new ArrayList<>();
		LocalTime start=LocalTime.of(6,0);
		LocalTime end = LocalTime.of(23, 0);
		
		while(start.isBefore(end)) {
			LocalTime nextHour=start.plusHours(1);
			boolean isBooked=bookedStartTimes.contains(nextHour);
			allSlots.add(new SlotRespDTO(start,nextHour,isBooked));
			start=nextHour;
		}
		
		return allSlots;
	}
	
	//get booked slot
	@Override
	public BookedSlotDTO getBookedSlotById(Long slotId) {
		// TODO Auto-generated method stub
		Slot slot = slotDao.findByIdAndIsBookedTrue(slotId)
				.orElseThrow(() -> new NoSuchElementException("No booked slot found for ID: " + slotId));
		
		BookedSlotDTO dto = new BookedSlotDTO();
        dto.setSlotId(slot.getId());
        dto.setStartTime(slot.getStartTime());
        dto.setEndTime(slot.getEndTime());
        dto.setDate(slot.getDate());
		
		return dto;
	}
	
}


