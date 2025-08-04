package com.sunbeam.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunbeam.custom_exceptions.ResourceNotFoundException;
import com.sunbeam.dao.TurfDao;
import com.sunbeam.dto.TurfRequestDto;
import com.sunbeam.entities.Sports;
import com.sunbeam.entities.Turf;

@Service
public class TurfServiceImpl implements TurfService {

    @Autowired
    private TurfDao turfDao;

    @Autowired
    private ModelMapper modelMapper;

    
    @Override
    public Turf addTurf(TurfRequestDto dto) {
        Turf turf = modelMapper.map(dto, Turf.class);
        turf.setActive(false);
        return turfDao.save(turf);
    }


    @Override
    public Turf updateTurf(Long id, TurfRequestDto dto) {
        Turf existing = turfDao.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Turf not found with id: " + id));

        existing.setTurfName(dto.getTurfName());
        existing.setDescription(dto.getDescription());
        existing.setOwnerName(dto.getOwnerName());
        existing.setCity(dto.getCity());
        existing.setState(dto.getState());
        existing.setPinCode(dto.getPinCode());
        existing.setTurfLocation(dto.getTurfLocation());
        existing.setSports(dto.getSports()); // ✅ updated for list of sports

        return turfDao.save(existing);
    }



    @Override
    public List<Turf> getAllTurfs() {
        return turfDao.findAll();
    }

    @Override
    public List<Turf> getActiveTurfs() {
        return turfDao.findByIsActive(true);
    }

    @Override
    public Turf getTurfById(Long id) {
        return turfDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Turf not found with id: " + id));
    }

    @Override
    public void deleteTurf(Long id) {
        Turf turf = getTurfById(id);
        turfDao.delete(turf);
    }

    @Override
    public Turf approveTurf(Long id) {
        Turf turf = getTurfById(id);
        turf.setActive(true);
        return turfDao.save(turf);
    }

	
}
