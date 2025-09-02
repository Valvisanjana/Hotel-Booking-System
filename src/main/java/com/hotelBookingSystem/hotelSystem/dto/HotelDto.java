package com.hotelBookingSystem.hotelSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelDto {
	private int hotelId;
	private String hotelName;
	private String location;
    private String description;

}
