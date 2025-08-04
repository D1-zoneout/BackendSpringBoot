package com.sunbeam.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import com.sunbeam.custom_exceptions.ApiException;
//import com.sunbeam.custom_exceptions.ResourceNotFoundException;
import com.sunbeam.dao.UserDao;
import com.sunbeam.dto.AdminPanelUserListDTO;
import com.sunbeam.dto.ApiResponse;
import com.sunbeam.dto.AuthRequestDTO;
import com.sunbeam.dto.UserRequestDTO;
import com.sunbeam.dto.UserResponseDTO;
import com.sunbeam.entities.Roles;
import com.sunbeam.entities.User;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImp implements UserService {

//	@Autowired
	private final UserDao userdao;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private final PasswordEncoder passwordEncoder;// this is password encoder

	@Override
	public UserResponseDTO register(UserRequestDTO dto) {

		if (userdao.existsByEmail(dto.getEmail()))
			throw new ApiException("duplicate");

		User entity = mapper.map(dto, User.class);
		entity.setPassword(passwordEncoder.encode(dto.getPassword()));
		return mapper.map(userdao.save(entity), UserResponseDTO.class);
	}

	@Override
	public ApiResponse login(AuthRequestDTO dto) {
		Optional<User> entity = userdao.findByEmail(dto.getEmail());
		if (entity.isEmpty())
			throw new ApiException("invalid password");
		User user = entity.get();

		if (!passwordEncoder.matches(dto.getPassword(), user.getPassword()))
			throw new ApiException("invalid password ");
		return new ApiResponse("logged in successfully");
	}


	@Override
	public UserResponseDTO getById(Long id) {
		Optional<User> optionalUser = userdao.findById(id);

		if (optionalUser.isPresent()) {
			return mapper.map(optionalUser.get(), UserResponseDTO.class);
		} else {
			// Return a dummy DTO or null, based on how you want to handle it in the
			// controller
			throw new ApiException("user not found ");
		}
	}

	@Override
	public UserResponseDTO updateUser(Long id, UserRequestDTO dto) {
		Optional<User> optionalUser = userdao.findById(id);
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			user.setName(dto.getName());
			user.setMobileNo(dto.getMobileNo());
			user.setPassword(passwordEncoder.encode(dto.getPassword()));
			user.setUser(dto.getUser());
			return mapper.map(userdao.save(user), UserResponseDTO.class);

		} else {
			throw new ApiException("user not found ");
		}

	}

	@Override
	public ApiResponse deleteById(Long id) {

		if (!userdao.existsById(id)) {
			throw new RuntimeException("User not found with ID: " + id);
		}
		userdao.deleteById(id);
		return new ApiResponse("deleted succesffully");
	}

///for admin panel display all users
	@Override
	public List<AdminPanelUserListDTO> getAllUsers() {
		List<User> users = userdao.findByUser(Roles.USER);
		return users.stream().map(user -> mapper.map(user, AdminPanelUserListDTO.class)).collect(Collectors.toList());

	}

///for admin panel display all providers
	@Override
	public List<AdminPanelUserListDTO> getAllProviders() {
		// TODO Auto-generated method stub
		List<User> providers = userdao.findByUser(Roles.PROVIDER);
		return providers.stream().map(provider -> mapper.map(provider, AdminPanelUserListDTO.class))
				.collect(Collectors.toList());

	}

}
