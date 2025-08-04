package com.sunbeam.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewResponseDto {
    private Long id;
    private int rating;
    private String review;
    private String userName;
    private String turfName;
}