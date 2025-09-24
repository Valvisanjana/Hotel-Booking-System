package com.hotelBookingSystem.hotelSystem.service;

import java.util.List;

import com.hotelBookingSystem.hotelSystem.dto.StaffDto;

public interface StaffService {

	StaffDto addStaff(StaffDto staffDto);
	
	StaffDto getStaffByName(String staffName);
	
	StaffDto getStaffById(int staffId);
	
	List<StaffDto> getStaffsByHotelName(String hotelName);
	
	StaffDto editStaffInto(StaffDto staffDto, int staffId);
	
	String removeStaffById(int staffId);

}
