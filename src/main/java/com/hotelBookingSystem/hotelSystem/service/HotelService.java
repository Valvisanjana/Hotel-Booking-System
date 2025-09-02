package com.hotelBookingSystem.hotelSystem.service;

import java.util.List;

import com.hotelBookingSystem.hotelSystem.dto.HotelDto;

public interface HotelService {
      
	HotelDto addHotel(HotelDto hotel);
	
	HotelDto getHotelById(int id);
	
	HotelDto getHotelByName(String name);
	
	List<HotelDto> getHotels();
	
	HotelDto updateHotelInfo(HotelDto hotel);
	
	void deleteHotelById(int id);
}
