package com.sunbeam.service;

import java.util.List;

import com.sunbeam.dto.TurfRequestDto;
import com.sunbeam.entities.Turf;

public interface TurfService {
    Turf addTurf(TurfRequestDto dto);
    List<Turf> getAllTurfs();
    List<Turf> getActiveTurfs(); // added
    Turf getTurfById(Long id);
    void deleteTurf(Long id);
    Turf approveTurf(Long id);
    Turf updateTurf(Long id, TurfRequestDto turfDto);
}
