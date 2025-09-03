package com.hotelBookingSystem.hotelSystem.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelBookingSystem.hotelSystem.dto.RoomDto;
import com.hotelBookingSystem.hotelSystem.service.RoomService;

@RestController
@RequestMapping("/api/room")
public class RoomController {

	@Autowired
	private RoomService roomService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/add")
	public ResponseEntity<RoomDto> saveRoom(@RequestBody RoomDto roomDto) {
		return ResponseEntity.ok(roomService.addRoom(roomDto));
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/getBy/{id}")
	public ResponseEntity<RoomDto> getRoom(@PathVariable int id) {
		return ResponseEntity.ok(roomService.getById(id));
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping("/getByRoom/{Number}")
	public ResponseEntity<RoomDto> getRoomByNum(@PathVariable("Number") String roomNumber) {
		return ResponseEntity.ok(roomService.getByRoomNumber(roomNumber));
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping("/getByRoomType/{Type}")
	public ResponseEntity<RoomDto> getRoomByType(@PathVariable("Type") String roomType) {
		return ResponseEntity.ok(roomService.getByRoomType(roomType));
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/editRoom/{id}")
	public ResponseEntity<RoomDto> editRoom(@PathVariable int id, @RequestBody RoomDto roomDto) {
		return ResponseEntity.ok(roomService.editById(id, roomDto));
	}
	
	@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteRoom(@PathVariable int id) {
		return ResponseEntity.ok(roomService.deleteRoomById(id));
	}	
	
}
