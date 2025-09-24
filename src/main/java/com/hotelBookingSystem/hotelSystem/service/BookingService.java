package com.hotelBookingSystem.hotelSystem.service;

import java.util.List;

import com.hotelBookingSystem.hotelSystem.dto.BookingDto;

public interface BookingService {

	BookingDto doBook(BookingDto bookDto);

	BookingDto checkBookingByRoomId(int roomId, int bookingId);

	BookingDto checkBookingByGuestId(int guestId, int bookingId);

	List<BookingDto> getTotalBookings();

	BookingDto editBookingById(int bookingId, BookingDto bookingDto);

	String deleteBookingByGuestId(int guestId);

}
