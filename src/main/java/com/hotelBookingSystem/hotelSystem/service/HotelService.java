package com.hotelBookingSystem.hotelSystem.service;

import java.util.List;

import com.hotelBookingSystem.hotelSystem.dto.HotelDto;

public interface HotelService {

	HotelDto addHotel(HotelDto hotel);

	HotelDto getHotelById(int id);

	HotelDto getHotelByName(String hotelName);

	List<HotelDto> getHotels();

	HotelDto updateHotelInfo(HotelDto hotel, int id);

	String deleteHotelById(int id);
}
