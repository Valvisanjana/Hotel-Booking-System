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

import com.hotelBookingSystem.hotelSystem.dto.GuestDto;
import com.hotelBookingSystem.hotelSystem.service.GuestService;

@RestController
@RequestMapping("/api/guest")
public class GuestController {

	@Autowired
	private GuestService guestService;

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/add")
	public ResponseEntity<GuestDto> addGuest(@RequestBody GuestDto guestDto) {
		return ResponseEntity.ok(guestService.addGuest(guestDto));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/get/guest/{id}")
	public ResponseEntity<GuestDto> getById(@PathVariable("id") int GuestId) {
		return ResponseEntity.ok(guestService.getGuestById(GuestId));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/get/guest/{name}")
	public ResponseEntity<GuestDto> getByName(@PathVariable("name") String GuestName) {
		return ResponseEntity.ok(guestService.getGuesByName(GuestName));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/edit/guest/{id}")
	public ResponseEntity<GuestDto> editById(@PathVariable("id") int GuestId, @RequestBody GuestDto guestDto) {
		return ResponseEntity.ok(guestService.editGuestInfo(GuestId, guestDto));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/guest/{id}")
	public ResponseEntity<String> deleteGuest(@PathVariable("id") int GuestId) {
		return ResponseEntity.ok(guestService.removeGuestById(GuestId));
	}
}
