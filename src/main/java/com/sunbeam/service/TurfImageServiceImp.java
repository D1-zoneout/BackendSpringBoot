package com.sunbeam.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunbeam.dao.TurfDao;
import com.sunbeam.dao.TurfImageDao;
import com.sunbeam.entities.Turf;
import com.sunbeam.entities.TurfImage;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TurfImageServiceImp implements  TurfImageService{
	
	 @Autowired
	    private TurfImageDao turfimagedao;

	    @Autowired 
	    private TurfDao turfdao;

	    @Override
	    public List<TurfImage> addImagesToTurf(Long turfId, List<String> imageUrls) {
	        Turf turf = turfdao.findById(turfId)
	                .orElseThrow(() -> new RuntimeException("Turf not found"));

	        List<TurfImage> images = imageUrls.stream()
	                .map(url -> {
	                    TurfImage img = new TurfImage();
	                    img.setImageurl(url);
	                    img.setTurf(turf);
	                    return img;
	                })
	                .collect(Collectors.toList());

	        return turfimagedao.saveAll(images);
	    }
	}
