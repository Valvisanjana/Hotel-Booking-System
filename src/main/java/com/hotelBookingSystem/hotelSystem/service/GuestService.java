package com.hotelBookingSystem.hotelSystem.service;

import java.util.List;

import com.hotelBookingSystem.hotelSystem.dto.GuestDto;

public interface GuestService {

	GuestDto addGuest(GuestDto guestDto);
	
	GuestDto editGuestInfo(int guestId, GuestDto guestDto);
	
	List<GuestDto> getAll();
	
	GuestDto getGuestById(int guestId);
	
	GuestDto getGuesByName(String guestName);
	
	String removeGuestById(int guestId);
}
