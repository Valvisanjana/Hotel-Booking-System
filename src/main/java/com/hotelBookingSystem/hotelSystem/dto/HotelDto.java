package com.hotelBookingSystem.hotelSystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelDto {
	private int hotelId;
	
	@NotBlank(message = "hotel name is required")
	private String hotelName;
	
	@NotBlank(message = "location is required")
	private String location;
	
	@NotNull(message = "description cannot be null")
	private String description;

}
