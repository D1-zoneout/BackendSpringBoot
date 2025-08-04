package com.sunbeam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	
	@Autowired 	
	private UserService userservice;
	
	@GetMapping("/user") 
	public ResponseEntity<?> getAllUsers(){
		  return ResponseEntity.ok(userservice.getAllUsers());
	}
	 @GetMapping("/providers")
	    public ResponseEntity<?> getAllProviders() {
	        return ResponseEntity.ok(userservice.getAllProviders());
	    }
}
