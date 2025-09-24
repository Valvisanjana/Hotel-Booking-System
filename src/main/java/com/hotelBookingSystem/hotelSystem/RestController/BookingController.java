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

import com.hotelBookingSystem.hotelSystem.dto.BookingDto;
import com.hotelBookingSystem.hotelSystem.service.BookingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/book")
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@PreAuthorize("hasRole('USER')")
	@PostMapping("/book")
	public ResponseEntity<BookingDto> doBooking(@Valid @RequestBody BookingDto bookDto) {
		return ResponseEntity.ok(bookingService.doBook(bookDto));
	}

	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping("/check/booking/{roomId}/{bookingId}")
	public ResponseEntity<BookingDto> getBookingByRoom(@PathVariable int roomId, @PathVariable int bookingId) {
		return ResponseEntity.ok(bookingService.checkBookingByRoomId(roomId, bookingId));
	}

	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping("/check/booking/{guestId}/{bookingId}")
	public ResponseEntity<BookingDto> getBookingByGuest(@PathVariable int guestId, @PathVariable int bookingId) {
		return ResponseEntity.ok(bookingService.checkBookingByRoomId(guestId, bookingId));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/get/list")
	public ResponseEntity<List<BookingDto>> getBookingList() {
		return ResponseEntity.ok(bookingService.getTotalBookings());
	}

	@PreAuthorize("hasRole('USER')")
	@PutMapping("/edit/booking/{id}")
	public ResponseEntity<BookingDto> updateBooking(@PathVariable("id") int bookingId,
			@RequestBody BookingDto bookDto) {
		return ResponseEntity.ok(bookingService.editBookingById(bookingId, bookDto));
	}

	@PreAuthorize("hasRole('USER')")
	@DeleteMapping("/delete/Booking/{id}")
	public ResponseEntity<String> deleteBooking(@PathVariable("id") int guestId) {
		return ResponseEntity.ok(bookingService.deleteBookingByGuestId(guestId));
	}
}
