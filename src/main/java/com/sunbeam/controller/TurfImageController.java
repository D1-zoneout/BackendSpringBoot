package com.sunbeam.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.dto.TurfImageResponseDTO;
import com.sunbeam.dto.TurfUploadImageDTO;
import com.sunbeam.entities.TurfImage;
import com.sunbeam.service.TurfImageService;

@RestController
@RequestMapping("/api/turf-images")
public class TurfImageController {
   
   @Autowired	
	private  TurfImageService turfImageService; 
   
   
	@PostMapping("/add/{turfId}")
	
	public ResponseEntity<?>addNewTurf(@PathVariable Long turfId , @RequestBody TurfUploadImageDTO dto){
		
		      List<TurfImage> savedImages =turfImageService.addImagesToTurf(turfId , dto.getImageUrls());
		      List<TurfImageResponseDTO> response = savedImages.stream()
		    	        .map(img -> new TurfImageResponseDTO(img.getId(), img.getImageurl()))
		    	        .collect(Collectors.toList());
		      
		        return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
}
