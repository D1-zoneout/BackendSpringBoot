package com.sunbeam.dto;

import java.util.List;
import java.util.Set;

import com.sunbeam.entities.Facility;
import com.sunbeam.entities.Sports;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TurfRequestDto {

    @NotBlank(message = "Turf name is required")
    private String turfName;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Owner name is required")
    private String ownerName;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "State is required")
    private String state;

    @Pattern(regexp = "\\d{6}", message = "Invalid pin code")
    private String pinCode;

    @NotBlank(message = "Turf location is required")
    private String turfLocation;

    @NotBlank(message = "Sport is required")
    private Set<Sports> sports;
    @NotBlank(message = "Facilities is required")
    private Set<Facility> facility;
}
