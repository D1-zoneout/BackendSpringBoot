package com.sunbeam.controller;

import com.sunbeam.dto.TurfRequestDto;
import com.sunbeam.dto.TurfResponseDto;
import com.sunbeam.entities.Sports;
import com.sunbeam.entities.Turf;
import com.sunbeam.service.TurfService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/turfs")
public class TurfController {

    @Autowired
    private TurfService turfService;

    @Autowired
    private ModelMapper modelMapper;

    // ➤ POST: Add a new turf
    @PostMapping()
    public TurfResponseDto addTurf(@RequestBody TurfRequestDto dto) {
        Turf saved = turfService.addTurf(dto);
        return modelMapper.map(saved, TurfResponseDto.class);
    }

    // ➤ PUT: Update an existing turf by ID
    @PutMapping("/{id}")
    public TurfResponseDto updateTurf(@PathVariable Long id, @RequestBody TurfRequestDto dto) {
        Turf updated = turfService.updateTurf(id, dto);
        return modelMapper.map(updated, TurfResponseDto.class);
    }

    // ➤ GET: Get all turfs
    @GetMapping
    public List<TurfResponseDto> getAllTurfs() {
        return turfService.getAllTurfs()
                .stream()
                .map(t -> modelMapper.map(t, TurfResponseDto.class))
                .collect(Collectors.toList());
    }

    // ➤ GET: Get turf by ID
    @GetMapping("/{id}")
    public TurfResponseDto getTurfById(@PathVariable Long id) {
        Turf turf = turfService.getTurfById(id);
        return modelMapper.map(turf, TurfResponseDto.class);
    }

    // ➤ DELETE: Delete turf by ID
    @DeleteMapping("/{id}")
    public void deleteTurf(@PathVariable Long id) {
        turfService.deleteTurf(id);
    }

    // ➤ PUT: Approve turf by ID
    @PutMapping("/approve/{id}")
    public TurfResponseDto approveTurf(@PathVariable Long id) {
        Turf turf = turfService.approveTurf(id);
        return modelMapper.map(turf, TurfResponseDto.class);
    }
} 
