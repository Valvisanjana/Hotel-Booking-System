package com.hotelBookingSystem.hotelSystem.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuestDto {

	private int guestId;
	
	@NotBlank(message = "name is required")
	private String guestName;
	
	@NotBlank(message = "email is Required")
	@Email(message = "Enter a valid email")
	private String email;
	
	@NotBlank(message = "contact Number is required")
	private String phone_Number;
	
	private String id_Proof;
	
	@NotNull(message = "check in date is required")
	private LocalDateTime check_in_date;
	private LocalDateTime check_out_date;
}
