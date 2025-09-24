package com.hotelBookingSystem.hotelSystem.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StaffDto {
	private int staffId;
	private String staffName;
	private String email;
	private String phone_Number;
	private String role;
	private LocalDate joiningDate;

}
