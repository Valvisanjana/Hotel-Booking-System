package com.hotelBookingSystem.hotelSystem.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelBookingSystem.hotelSystem.Exception.GuestNotFoundException;
import com.hotelBookingSystem.hotelSystem.dto.GuestDto;
import com.hotelBookingSystem.hotelSystem.entity.Guest;
import com.hotelBookingSystem.hotelSystem.repository.GuestRepository;
import com.hotelBookingSystem.hotelSystem.service.GuestService;

@Service
public class GuestServiceImpl implements GuestService {

	@Autowired
	private GuestRepository guestRepo;

	@Autowired
	private ModelMapper modelMap;

	private Guest convertToEntity(GuestDto guestDto) {
		return modelMap.map(guestDto, Guest.class);
	}

	private GuestDto convertToDto(Guest guest) {
		return modelMap.map(guest, GuestDto.class);
	}

	@Override
	public GuestDto addGuest(GuestDto guestDto) {
		Guest guest = convertToEntity(guestDto);
		Guest savedGuest = guestRepo.save(guest);
		return convertToDto(savedGuest);
	}

	@Override
	public GuestDto editGuestInfo(int guestId, GuestDto guestDto) {
		Guest guest = guestRepo.findById(guestId)
				.orElseThrow(() -> new GuestNotFoundException("Guest Not Found With Id:" + guestId));

		guest.setGuestName(guestDto.getGuestName());
		guest.setEmail(guestDto.getEmail());
		guest.setPhone_Number(guestDto.getPhone_Number());
		guest.setId_Proof(guestDto.getId_Proof());
		guest.setCheck_in_date(guestDto.getCheck_in_date());
		guest.setCheck_out_date(guestDto.getCheck_out_date());

		Guest updatedGuest = guestRepo.save(guest);

		return convertToDto(updatedGuest);
	}

	@Override
	public List<GuestDto> getAll() {
		List<Guest> guest = guestRepo.findAll();
		return guest.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	@Override
	public GuestDto getGuestById(int guestId) {
		Guest guest = guestRepo.findById(guestId)
				.orElseThrow(() -> new GuestNotFoundException("Guest Not Found With Id:" + guestId));

		return convertToDto(guest);
	}

	@Override
	public GuestDto getGuesByName(String guestName) {
		Guest guest = guestRepo.findByGuestName(guestName)
				.orElseThrow(() -> new GuestNotFoundException("Guest Not Found With Name: " + guestName));

		return convertToDto(guest);
	}

	@Override
	public String removeGuestById(int guestId) {
		Guest guest = guestRepo.findById(guestId)
				.orElseThrow(() -> new GuestNotFoundException("Guest Not Found With Id:" + guestId));

		guestRepo.delete(guest);

		return "Guest Deleted";
	}

}
