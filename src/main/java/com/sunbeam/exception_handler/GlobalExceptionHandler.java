package com.sunbeam.exception_handler;

import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sunbeam.custom_exceptions.ApiException;
import com.sunbeam.custom_exceptions.ResourceNotFoundException;
import com.sunbeam.dto.ApiResponse;

@RestControllerAdvice
/*
 * Represents spring bean - containing centralized exc handling advice to ALL
 * Rest Controllers Advice - "You don't add repititive exc handling code , I
 * will do it for you"
 * 
 * @RestControllerAdvice=@ControllerAdvice + @ResponseBody - added implicitly on
 * ret type of exc handling methods.
 */
public class GlobalExceptionHandler {
	// add exc handling method
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<?> handleApiException(ApiException e) {
		System.out.println("in handle api exc");
		return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(e.getMessage()));
	}

	// add exc handling method
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException e) {
		System.out.println("in handle res not found exc");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
	}

	// add exc handling method
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValidException
	(MethodArgumentNotValidException e) {
		System.out.println("in handle P.L validation failures - @Valid ");
		//create Map of rejected fields n error messages
		//eg - firstName - mandatory field.....
		//1. get list of rejected fields 
		List<FieldError> fieldErrors = e.getFieldErrors();
		//2. Convert -> Map<Key - String fieldName , 
		//Value - String - err mesg>
//		fieldErrors.stream() //Stream<FieldError>
//		.collect(Collectors.toMap
//				(FieldError::getField,
//				FieldError::getDefaultMessage);
		 Map<String,String> errorMap=new HashMap<>();
		 fieldErrors.forEach(fieldError -> 
		 errorMap.put(fieldError.getField(), 
				 fieldError.getDefaultMessage()));
		return ResponseEntity.status
				(HttpStatus.BAD_REQUEST)
				.body(errorMap);
	}

	// equivalent to catch-all
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception e) {
		System.out.println("in catch all exc " + e);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage()));
	}

}
