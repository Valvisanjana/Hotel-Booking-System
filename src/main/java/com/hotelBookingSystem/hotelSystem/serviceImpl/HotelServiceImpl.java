package com.hotelBookingSystem.hotelSystem.serviceImpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelBookingSystem.hotelSystem.dto.HotelDto;
import com.hotelBookingSystem.hotelSystem.entity.Hotel;
import com.hotelBookingSystem.hotelSystem.repository.HotelRepository;
import com.hotelBookingSystem.hotelSystem.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService{

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HotelDto getHotelByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HotelDto> getHotels() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HotelDto updateHotelInfo(HotelDto hotel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteHotelById(int id) {
		// TODO Auto-generated method stub
		
	}

}
