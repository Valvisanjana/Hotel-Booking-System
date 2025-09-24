package com.hotelBookingSystem.hotelSystem.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelBookingSystem.hotelSystem.Exception.BookingNotFoundException;
import com.hotelBookingSystem.hotelSystem.Exception.GuestNotFoundException;
import com.hotelBookingSystem.hotelSystem.Exception.RoomNotFoundException;
import com.hotelBookingSystem.hotelSystem.dto.BookingDto;
import com.hotelBookingSystem.hotelSystem.entity.Booking;
import com.hotelBookingSystem.hotelSystem.entity.Guest;
import com.hotelBookingSystem.hotelSystem.entity.Room;
import com.hotelBookingSystem.hotelSystem.repository.BookingRepository;
import com.hotelBookingSystem.hotelSystem.repository.GuestRepository;
import com.hotelBookingSystem.hotelSystem.repository.RoomRepository;
import com.hotelBookingSystem.hotelSystem.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingRepository bookingRepo;

	@Autowired
	private RoomRepository roomRepo;

	@Autowired
	private GuestRepository guestRepo;

	@Autowired
	private ModelMapper modelMap;

	private Booking convertToEntity(BookingDto bookDto) {
		return modelMap.map(bookDto, Booking.class);
	}

	private BookingDto convertToDto(Booking booking) {
		return modelMap.map(booking, BookingDto.class);
	}

	@Override
	public BookingDto doBook(BookingDto bookDto) {
		Booking booking = convertToEntity(bookDto);
		bookingRepo.save(booking);
		return convertToDto(booking);
	}

	@Override
	public BookingDto checkBookingByRoomId(int roomId, int bookingId) {
		Room room = roomRepo.findById(roomId)
				.orElseThrow(() -> new RoomNotFoundException("Room NOt Found With Id: " + roomId));

		Booking booking = bookingRepo.findById(bookingId)
				.orElseThrow(() -> new BookingNotFoundException("Booking Not Found with Room Id :" + bookingId));

		if (booking.getRoomId() != room.getRoomId()) {
	        throw new RuntimeException("Booking " + bookingId + " does not belong to Room " + roomId);
	    }
		return convertToDto(booking);
	}

	@Override
	public BookingDto checkBookingByGuestId(int guestId, int bookingId) {
		Guest guest = guestRepo.findById(guestId)
				.orElseThrow(() -> new GuestNotFoundException("Guest Not Found with Id:" + guestId));

		Booking booking = bookingRepo.findById(bookingId)
				.orElseThrow(() -> new BookingNotFoundException("Booking Not Found with Room Id :" + bookingId));

		if (booking.getGuest() == null || booking.getGuest().getGuestId() != guest.getGuestId()) {
	        throw new RuntimeException("Booking " + bookingId + " does not belong to Guest " + guestId);
	    }

		return convertToDto(booking);
	}

	@Override
	public List<BookingDto> getTotalBookings() {
		List<Booking> blist = bookingRepo.findAll();
		return blist.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	public BookingDto editBookingById(int bookingId, BookingDto bookingDto) {
		Booking book = bookingRepo.findById(bookingId)
				.orElseThrow(() -> new BookingNotFoundException("Booking Not Found with Id:" + bookingId));

		book.setRoomId(bookingDto.getRoomId());
		book.setCheck_in_date(bookingDto.getCheck_in_date());
		book.setCheck_out_date(bookingDto.getCheck_out_date());
		book.setTotal_amount(bookingDto.getTotal_amount());

		Booking updateBook = bookingRepo.save(book);
		return convertToDto(updateBook);
	}

	@Override
	public String deleteBookingByGuestId(int guestId) {
		Guest guest = guestRepo.findById(guestId)
				.orElseThrow(() -> new GuestNotFoundException("Guest Not Found with Id:" + guestId));

		Booking booking = bookingRepo.findByGuest(guest);

		if (booking == null) {
			throw new BookingNotFoundException("Booking Not Found with guest :" + guest);
		}

		bookingRepo.delete(booking);
		return "booking deleted for guest id: " + guestId;
	}

}
