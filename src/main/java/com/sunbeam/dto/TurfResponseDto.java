package com.sunbeam.dto;

import java.util.Set;

import com.sunbeam.entities.Facility;
import com.sunbeam.entities.Sports;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TurfResponseDto {
    private Long id;
    private String turfName;
    private String description;
    private String ownerName;
    private String city;
    private String state;
    private String pinCode;
    private boolean isActive;
    private String turfLocation;
    private Set<Sports> sports;
    private Set<Facility> facility;
}
