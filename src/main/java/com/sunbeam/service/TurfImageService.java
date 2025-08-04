package com.sunbeam.service;

import java.util.List;

import com.sunbeam.entities.TurfImage;

public interface TurfImageService {

	  List<TurfImage> addImagesToTurf(Long turfId, List<String> imageUrls);

}
