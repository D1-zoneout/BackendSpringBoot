package com.sunbeam.dto;

import com.sunbeam.entities.Roles;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserResponseDTO {

	private String name;
	private String email;
	private String mobileNo;
//	private String Password;//password will not be shared  as  the return...
	public Roles user;
}
