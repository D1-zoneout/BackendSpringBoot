package com.sunbeam.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.dto.ApiResponse;
import com.sunbeam.dto.AuthRequestDTO;
import com.sunbeam.dto.UserRequestDTO;
import com.sunbeam.dto.UserResponseDTO;
import com.sunbeam.service.UserService;

import jakarta.validation.Valid;
//import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
     
	private final UserService userservice;
	
	@PostMapping("/register")
	public ResponseEntity<?> userRegister(@RequestBody @Valid UserRequestDTO dto){
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(userservice.register(dto));//token 
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> userLogin(@RequestBody AuthRequestDTO dto ){
		      ApiResponse dto1  = userservice.login(dto);
		    		  
		return ResponseEntity.ok(dto1);
	}

	
	@GetMapping("/{id}")
	
	public ResponseEntity<?>getUserById(@PathVariable Long id ){
		UserResponseDTO res = userservice.getById(id);
		return ResponseEntity.ok(res);
	}
	
	//
	@PutMapping("/user/{id}")
	public ResponseEntity<?>updateUser(@PathVariable Long id , @RequestBody UserRequestDTO dto){
		
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(userservice.updateUser(id ,dto));
	}
	
	
	//optional delete mapping for hard delete
	@DeleteMapping("/user/{id}")
	public ResponseEntity<?>deleteUser(@PathVariable Long id){
		  
		return ResponseEntity.ok(userservice.deleteById(id));
	}
}
