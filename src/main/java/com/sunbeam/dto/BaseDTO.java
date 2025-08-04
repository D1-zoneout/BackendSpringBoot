package com.sunbeam.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaseDTO {	
	private Long id;
	private LocalDateTime creationDate;
	private LocalDateTime updatedOn;

}
