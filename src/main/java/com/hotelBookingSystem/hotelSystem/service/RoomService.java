package com.hotelBookingSystem.hotelSystem.service;

import java.util.List;

import com.hotelBookingSystem.hotelSystem.dto.RoomDto;

public interface RoomService {

	RoomDto addRoom(RoomDto roomDto);

	RoomDto getById(int id);

	RoomDto getByRoomNumber(String roomNumber);

	RoomDto getByRoomType(String roomType);

	List<RoomDto> getAllRooms();

	RoomDto editById(int id, RoomDto roomDto);

	String deleteRoomById(int id);

}
