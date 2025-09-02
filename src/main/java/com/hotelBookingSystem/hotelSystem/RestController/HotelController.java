package com.hotelBookingSystem.hotelSystem.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	
	
	
}
