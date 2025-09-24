package com.hotelBookingSystem.hotelSystem.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelBookingSystem.hotelSystem.Exception.AddressNotFoundException;
import com.hotelBookingSystem.hotelSystem.Exception.GuestNotFoundException;
import com.hotelBookingSystem.hotelSystem.dto.AddressDto;
import com.hotelBookingSystem.hotelSystem.entity.Address;
import com.hotelBookingSystem.hotelSystem.entity.Guest;
import com.hotelBookingSystem.hotelSystem.repository.AddressRepository;
import com.hotelBookingSystem.hotelSystem.repository.GuestRepository;
import com.hotelBookingSystem.hotelSystem.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepo;

	@Autowired
	private GuestRepository guestRepo;

	@Autowired
	private ModelMapper modelMap;

	private Address convertToEntity(AddressDto addressDto) {
		return modelMap.map(addressDto, Address.class);
	}

	private AddressDto convertToDto(Address address) {
		return modelMap.map(address, AddressDto.class);
	}

	@Override
	public AddressDto addAddress(AddressDto addressDto) {
		Address address = convertToEntity(addressDto);
		addressRepo.save(address);
		return convertToDto(address);
	}

	@Override
	public AddressDto getAddressByGuestEmail(String email) {
		Guest guest = guestRepo.findByEmail(email)
				.orElseThrow(() -> new GuestNotFoundException("Guest Not Found with: " + email));

		Address address = guest.getAddress();
		if (address == null) {
			throw new AddressNotFoundException("Address not found for guest with email: " + email);
		}
		return convertToDto(address);
	}

	@Override
	public AddressDto editAddressByGuestName(String guestName, AddressDto addressDto) {
		Guest guest = guestRepo.findByGuestName(guestName)
				.orElseThrow(() -> new GuestNotFoundException("Guest Not Found With Name: " + guestName));

		Address address = guest.getAddress();
		if (address == null) {
			throw new AddressNotFoundException("Address not found with: " + guestName);
		}
 
		address.setStreet(addressDto.getStreet());
		address.setCity(addressDto.getCity());
		address.setState(addressDto.getState());
		address.setCountry(addressDto.getCountry());
		address.setPincode(addressDto.getPincode());

		Address updatedAddress = addressRepo.save(address);

		return convertToDto(updatedAddress);
	}

	@Override
	public String deleteAddressByGuestId(int guestId) {
		Guest guest = guestRepo.findById(guestId)
				.orElseThrow(() -> new GuestNotFoundException("Guest Not Found for Id: " + guestId));

		Address address = guest.getAddress();
		if (address == null) {
			throw new AddressNotFoundException("Address not found for Id : " + guestId);
		}

		addressRepo.delete(address);
		return "Address deleted successfully for guest ID: " + guestId;
	}

}
