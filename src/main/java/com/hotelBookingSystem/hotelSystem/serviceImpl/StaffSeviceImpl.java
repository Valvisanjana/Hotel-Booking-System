package com.hotelBookingSystem.hotelSystem.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelBookingSystem.hotelSystem.Exception.StaffNotFoundException;
import com.hotelBookingSystem.hotelSystem.dto.StaffDto;
import com.hotelBookingSystem.hotelSystem.entity.Staff;
import com.hotelBookingSystem.hotelSystem.repository.StaffRepository;
import com.hotelBookingSystem.hotelSystem.service.StaffService;

@Service
public class StaffSeviceImpl implements StaffService {

	@Autowired
	private StaffRepository staffRepo;

	@Autowired
	private ModelMapper modelMap;

	private Staff convertToEntity(StaffDto staffDto) {
		return modelMap.map(staffDto, Staff.class);
	}

	private StaffDto convertToDto(Staff staff) {
		return modelMap.map(staff, StaffDto.class);
	}

	@Override
	public StaffDto addStaff(StaffDto staffDto) {
		Staff staff = convertToEntity(staffDto); 
		staffRepo.save(staff);
		return convertToDto(staff);
	}

	@Override
	public StaffDto getStaffByName(String staffName) {
		Staff staff = staffRepo.findBystaffName(staffName)
				.orElseThrow(() -> new StaffNotFoundException("Staff Not Found With :" + staffName));
		return convertToDto(staff);
	}

	@Override
	public StaffDto getStaffById(int staffId) {
		Staff staff = staffRepo.findById(staffId)
				.orElseThrow(() -> new StaffNotFoundException("Staff Not Found With Id:" + staffId));
		return convertToDto(staff);
	}

	@Override
	public List<StaffDto> getStaffsByHotelName(String hotelName) {
		List<Staff> staffs = staffRepo.findByHotel_hotelName(hotelName);
		return staffs.stream()
				 .map(staff -> new StaffDto(
						    staff.getStaffId(),
		                    staff.getStaffName(),
		                    staff.getEmail(),
		                    staff.getPhone_Number(),
		                    staff.getRole(),
		                    staff.getJoiningDate()
		            ))
		            .collect(Collectors.toList());
	}
 
	@Override
	public StaffDto editStaffInto(StaffDto staffDto, int staffId) {
		Staff staff = staffRepo.findById(staffId)
				.orElseThrow(() -> new StaffNotFoundException("Staff Not Found With Id:" + staffId));

		staff.setStaffName(staffDto.getStaffName());
		staff.setEmail(staffDto.getEmail());
		staff.setRole(staffDto.getRole());
		staff.setPhone_Number(staffDto.getPhone_Number());
		staff.setJoiningDate(staffDto.getJoiningDate());

		Staff updatedStaff = staffRepo.save(staff);
		return convertToDto(updatedStaff);
	}

	@Override
	public String removeStaffById(int staffId) {
		Staff staff = staffRepo.findById(staffId)
				.orElseThrow(() -> new StaffNotFoundException("Staff Not Found With Id:" + staffId));
		staffRepo.delete(staff);
		return "Staff removed";
	}

}
