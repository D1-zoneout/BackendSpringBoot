package com.sunbeam.service;

import java.time.LocalDate;
import java.util.List;

import com.sunbeam.dto.BookedSlotDTO;
import com.sunbeam.dto.SlotRespDTO;
import com.sunbeam.entities.Slot;

public interface SlotService {

	List<SlotRespDTO> getSlotsByTurfAndDate(Long turfId, LocalDate date);

	BookedSlotDTO getBookedSlotById(Long slotId);

}
