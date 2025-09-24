package com.hotelBookingSystem.hotelSystem.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StaffDto {
	private int staffId;
	
	@NotBlank(message = "staff Name is Required")
	private String staffName;
	
	@NotBlank(message = "email is Required")
	@Email(message = "Enter a valid email")
	private String email;
	
	@NotBlank(message = "contact Number is required")
	private String phone_Number;

	@NotNull(message = "Age cannot be null")
	private String age;
	
	@NotBlank(message = "role is required")
	private String role;
	
	@NotBlank(message = "joining date of staff is required")
	private LocalDate joiningDate;

}
