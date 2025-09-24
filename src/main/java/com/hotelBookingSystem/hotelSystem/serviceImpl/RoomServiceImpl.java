package com.hotelBookingSystem.hotelSystem.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelBookingSystem.hotelSystem.Exception.RoomNotFoundException;
import com.hotelBookingSystem.hotelSystem.dto.RoomDto;
import com.hotelBookingSystem.hotelSystem.entity.Room;
import com.hotelBookingSystem.hotelSystem.repository.RoomRepository;
import com.hotelBookingSystem.hotelSystem.service.RoomService;

@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomRepository roomRepo;

	@Autowired
	private ModelMapper modelMap;

	private Room convertToEntity(RoomDto roomDto) {
		return modelMap.map(roomDto, Room.class);
	}

	private RoomDto convertToDto(Room room) {
		return modelMap.map(room, RoomDto.class);
	}

	@Override
	public RoomDto addRoom(RoomDto roomDto) {
		Room room = convertToEntity(roomDto);
		Room newRoom = roomRepo.save(room);
		return convertToDto(newRoom);
	}

	@Override
	public RoomDto getById(int id) {
		Room room = roomRepo.findById(id).orElseThrow(() -> new RoomNotFoundException("Room Not Found with id: " + id));

		return convertToDto(room);
	}

	@Override
	public RoomDto getByRoomNumber(String roomNumber) {
		Room room = roomRepo.findByRoomNumber(roomNumber);
		if (room == null) {
			throw new RoomNotFoundException("Room Not Found with roomNumber: " + roomNumber);
		}
		return convertToDto(room);
	}

	@Override
	public RoomDto getByRoomType(String roomType) {
		Room room = roomRepo.findByRoomType(roomType);
		if (room == null) {
			throw new RoomNotFoundException("Room Not Found with roomType: " + roomType);
		}
		return convertToDto(room);
	}

	@Override
	public RoomDto editById(int id, RoomDto roomDto) {
		Room room = roomRepo.findById(id).orElseThrow(() -> new RoomNotFoundException("Room Not Found with id: " + id));

		room.setRoomNumber(roomDto.getRoomNumber());
		room.setRoomType(roomDto.getRoomType());
		room.setPricePerNight(roomDto.getPricePerNight());
		room.setStatus(roomDto.getStatus());

		Room updateRoom = roomRepo.save(room);
		return convertToDto(updateRoom);
	}

	@Override
	public String deleteRoomById(int id) {
		Room room = roomRepo.findById(id).orElseThrow(() -> new RoomNotFoundException("Room Not Found with id: " + id));

		if (room != null) {
			roomRepo.deleteById(id);
		}
		return "Room deleted successfully!";
	}

	@Override
	public List<RoomDto> getAllRooms() {
		List<Room> rlist = roomRepo.findAll();
		return rlist.stream().map(this::convertToDto).collect(Collectors.toList());
	}

}
