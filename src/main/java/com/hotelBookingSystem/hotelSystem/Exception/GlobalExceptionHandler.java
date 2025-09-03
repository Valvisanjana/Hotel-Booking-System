package com.hotelBookingSystem.hotelSystem.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hotelBookingSystem.hotelSystem.dto.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler { 
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleUserNotFound (UserNotFoundException ex){
		ErrorResponse error = new ErrorResponse(ex.getMessage(), LocalDateTime.now());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND); 
	}
	
	@ExceptionHandler(HotelNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleHotelNotFound (HotelNotFoundException ex) {
		ErrorResponse error = new ErrorResponse(ex.getMessage(), LocalDateTime.now());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(RoomNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleRoomNotFound (RoomNotFoundException ex) {
		ErrorResponse error = new ErrorResponse(ex.getMessage(), LocalDateTime.now());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);		
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException (Exception ex) {
		return new ResponseEntity<>("Something went wrong" + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); 
	}
} 
