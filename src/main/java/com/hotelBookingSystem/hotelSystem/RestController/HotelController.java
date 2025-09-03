package com.hotelBookingSystem.hotelSystem.RestController;

import java.util.List;

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

import com.hotelBookingSystem.hotelSystem.dto.HotelDto;
import com.hotelBookingSystem.hotelSystem.service.HotelService;

@RestController
@RequestMapping("api/hotel")
public class HotelController {

	@Autowired
	private HotelService hotelService;

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/add")
	public ResponseEntity<HotelDto> addHotel(@RequestBody HotelDto dto) {
		HotelDto savedhotel = hotelService.addHotel(dto);
		return ResponseEntity.ok(savedhotel);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/getHotelBy/{id}")
	public ResponseEntity<HotelDto> getHotelById(@PathVariable int id) {
		HotelDto getHotel = hotelService.getHotelById(id);
		return ResponseEntity.ok(getHotel);
	}

	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping("/getHotel/{name}")
	public ResponseEntity<HotelDto> getHotelByname(@PathVariable("name") String hotelName) {
		HotelDto getHotel = hotelService.getHotelByName(hotelName);
		return ResponseEntity.ok(getHotel);
	}

	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping("/getHotelList")
	public ResponseEntity<List<HotelDto>> getAll() {
		return ResponseEntity.ok(hotelService.getHotels());
	} 

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/edit/{id}")
	public ResponseEntity<HotelDto> editHotel(@RequestBody HotelDto dto, @PathVariable int id) {
		HotelDto updatedHotel = hotelService.updateHotelInfo(dto, id);
		return ResponseEntity.ok(updatedHotel);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<String> deleteHotel(@PathVariable int id) {
		return ResponseEntity.ok(hotelService.deleteHotelById(id));
	}

}
