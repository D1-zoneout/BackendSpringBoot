package com.sunbeam.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.dto.BookedSlotDTO;
import com.sunbeam.dto.SlotRespDTO;
import com.sunbeam.service.SlotService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/slots")

public class SlotController {
	private final SlotService slotService;
	
	//get all slots for a turf on a given date
	
	@GetMapping("/available")
	public ResponseEntity<?> listSlotsByTurfAndDate(
            @RequestParam Long turfId,
            @RequestParam LocalDate date) {

        System.out.println("in listSlotsByTurfAndDate turfId=" + turfId + ", date=" + date);

        List<SlotRespDTO> slots = slotService.getSlotsByTurfAndDate(turfId, date);

        if (slots.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        return ResponseEntity.ok(slots);
	}
	
	@GetMapping("/booked/{slotId}")
	public ResponseEntity<BookedSlotDTO> getBookedSlot(@PathVariable Long slotId)
	{
		BookedSlotDTO dto=slotService.getBookedSlotById(slotId);
		return ResponseEntity.ok(dto);
	}
	

}
