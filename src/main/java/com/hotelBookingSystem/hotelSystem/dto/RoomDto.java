package com.hotelBookingSystem.hotelSystem.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDto {
	private int roomId;
	
	@NotNull(message = "room number is required")
	private String roomNumber;
	
	private String roomType;
	private double pricePerNight;
	private String status;
}
