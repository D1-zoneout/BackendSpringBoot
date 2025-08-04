package com.sunbeam.service;

import java.util.List;

import com.sunbeam.dto.AdminPanelUserListDTO;
import com.sunbeam.dto.ApiResponse;

//import java.util.Optional;

import com.sunbeam.dto.AuthRequestDTO;
import com.sunbeam.dto.UserRequestDTO;
import com.sunbeam.dto.UserResponseDTO;

//import com.sunbeam.entities.User;

public interface UserService {
	UserResponseDTO register(UserRequestDTO dto);

	ApiResponse login(AuthRequestDTO dto);

	UserResponseDTO getById(Long id);

	UserResponseDTO updateUser(Long id, UserRequestDTO dto);

	ApiResponse deleteById(Long id);

	List<AdminPanelUserListDTO> getAllUsers();

	List<AdminPanelUserListDTO> getAllProviders();
}
