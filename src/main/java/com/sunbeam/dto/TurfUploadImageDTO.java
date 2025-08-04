package com.sunbeam.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TurfUploadImageDTO {
	private List<String> imageUrls;  // List of Cloudinary URLs
}
