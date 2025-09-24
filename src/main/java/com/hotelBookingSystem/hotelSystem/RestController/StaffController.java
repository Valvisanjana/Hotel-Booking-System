package com.hotelBookingSystem.hotelSystem.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelBookingSystem.hotelSystem.dto.StaffDto;
import com.hotelBookingSystem.hotelSystem.service.StaffService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

	@Autowired
	private StaffService staffService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/addstaff")
	public ResponseEntity<StaffDto> addStaff(@Valid @RequestBody StaffDto staffDto){
		return ResponseEntity.ok(staffService.addStaff(staffDto));
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/get/by/{name}")
	public ResponseEntity<StaffDto> getStaffByName(@PathVariable("name") String staffName){
		return ResponseEntity.ok(staffService.getStaffByName(staffName));
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/get/by/{id}")
	public ResponseEntity<StaffDto> getStaffById(@PathVariable("id") int staffId){
		return ResponseEntity.ok(staffService.getStaffById(staffId));
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/get/all/{hotelName}")
	public ResponseEntity<List<StaffDto>> getStaffByHotelName(@PathVariable String hotelName){
		return ResponseEntity.ok(staffService.getStaffsByHotelName(hotelName));
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/edit/{id}")
	public ResponseEntity<StaffDto> editStaff(@PathVariable("id") int staffId, @RequestBody StaffDto staffDto){
		return ResponseEntity.ok(staffService.editStaffInto(staffDto, staffId));
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/staff/{id}")
	public ResponseEntity<String> removeStaff(@PathVariable("id") int staffId){
		return ResponseEntity.ok(staffService.removeStaffById(staffId));
	}
}
