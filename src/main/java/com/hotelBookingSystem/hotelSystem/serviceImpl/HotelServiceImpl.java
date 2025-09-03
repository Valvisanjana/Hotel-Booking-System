package com.hotelBookingSystem.hotelSystem.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelBookingSystem.hotelSystem.Exception.HotelNotFoundException;
import com.hotelBookingSystem.hotelSystem.dto.HotelDto;
import com.hotelBookingSystem.hotelSystem.entity.Hotel;
import com.hotelBookingSystem.hotelSystem.repository.HotelRepository;
import com.hotelBookingSystem.hotelSystem.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelRepository hotelRepo;

	@Autowired
	private ModelMapper modelMap;

	public Hotel convertToEntity(HotelDto hotelDto) {
		return modelMap.map(hotelDto, Hotel.class);
	}

	public HotelDto convertToDto(Hotel hotel) {
		return modelMap.map(hotel, HotelDto.class);
	}

	@Override
	public HotelDto addHotel(HotelDto hotelDto) {
		Hotel hotel = convertToEntity(hotelDto);
		Hotel newHotel = hotelRepo.save(hotel);
		return convertToDto(newHotel);
	}

	@Override
	public HotelDto getHotelById(int id) {
		Hotel hotel = hotelRepo.findById(id)
				.orElseThrow(() -> new HotelNotFoundException("Hotel Not Found with id :" + id));
		return convertToDto(hotel);
	}

	@Override
	public HotelDto getHotelByName(String hotelName) {
		Hotel hotel = hotelRepo.findByHotelName(hotelName)
				.orElseThrow(() -> new HotelNotFoundException("Hotel Not Found with name :" + hotelName));
		return convertToDto(hotel);
	}

	@Override
	public List<HotelDto> getHotels() {
		List<Hotel> hlist = hotelRepo.findAll();
		return hlist.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	@Override
	public HotelDto updateHotelInfo(HotelDto hDto, int id) {
		Hotel hotel = hotelRepo.findById(id)
				.orElseThrow(() -> new HotelNotFoundException("Hotel Not Found With id: " +id));
		
			hotel.setHotelName(hDto.getHotelName());
			hotel.setLocation(hDto.getLocation());
		    hotel.setDescription(hDto.getDescription());
			
			Hotel updatedHotel = hotelRepo.save(hotel);
		
		return convertToDto(updatedHotel);
	}

	@Override
	public String deleteHotelById(int id) {
		if (hotelRepo.findById(id) != null) {
			hotelRepo.deleteById(id);
		}
		return "deleted hotel record";
	}

}
