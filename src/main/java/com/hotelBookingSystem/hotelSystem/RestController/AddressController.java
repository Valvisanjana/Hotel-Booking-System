package com.hotelBookingSystem.hotelSystem.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotelBookingSystem.hotelSystem.dto.AddressDto;
import com.hotelBookingSystem.hotelSystem.service.AddressService;

@RestController
@RequestMapping("/api/address")
public class AddressController {

	@Autowired
	private AddressService addressService;
	
	@PreAuthorize("hasRole('USER')")
	@PostMapping("/add")
	public ResponseEntity<AddressDto> addAddress(@RequestBody AddressDto addressDto){
		return ResponseEntity.ok(addressService.addAddress(addressDto));
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/get/address/{email}")
	public ResponseEntity<AddressDto> getAddressByGuestEmail(@RequestParam String email){
		return ResponseEntity.ok(addressService.getAddressByGuestEmail(email));
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/get/address/{name}")
	public ResponseEntity<AddressDto> getAddressByGuestName(@RequestParam("name") String guestName, @RequestBody AddressDto addressDto){
		return ResponseEntity.ok(addressService.editAddressByGuestName(guestName, addressDto));
	}
	
	@PreAuthorize("hasRole('USER')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteAddressById(@RequestParam("id") int guestId){
		return ResponseEntity.ok(addressService.deleteAddressByGuestId(guestId));
	}
}
