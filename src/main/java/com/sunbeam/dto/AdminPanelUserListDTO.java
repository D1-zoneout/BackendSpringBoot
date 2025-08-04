package com.sunbeam.dto;

import com.sunbeam.entities.Roles;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AdminPanelUserListDTO {

	   private Long id;
	    private String name;
	    private String mobileNo;
	    private String email;
	    private Roles user;
}
