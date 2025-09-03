package com.hotelBookingSystem.hotelSystem.service;

import com.hotelBookingSystem.hotelSystem.dto.RoomDto;

public interface RoomService {
            
	RoomDto addRoom(RoomDto roomDto);
	
	RoomDto getById(int id);
	
	RoomDto getByRoomNumber(String roomNumber);
	
	RoomDto getByRoomType(String roomType);
	
	RoomDto editById(int id, RoomDto roomDto);
	
	String deleteRoomById(int id);
	
	
}
