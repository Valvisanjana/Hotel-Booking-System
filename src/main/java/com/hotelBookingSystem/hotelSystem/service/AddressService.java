package com.hotelBookingSystem.hotelSystem.service;

import com.hotelBookingSystem.hotelSystem.dto.AddressDto;

public interface AddressService {

	AddressDto addAddress(AddressDto addressDto);
	
	AddressDto getAddressByGuestEmail(String email);
	
	AddressDto editAddressByGuestName(String guestName, AddressDto addressDto);
	
	String deleteAddressByGuestId(int guestId);
}
