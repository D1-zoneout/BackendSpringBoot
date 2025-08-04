package com.sunbeam.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SlotRespDTO {
    private LocalTime startTime; 
    private LocalTime endTime;   
    private boolean isBooked;
}
