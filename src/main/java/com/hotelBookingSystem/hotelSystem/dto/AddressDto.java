package com.hotelBookingSystem.hotelSystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
	private int addressId;
	
	@NotBlank(message = "street is required")
	private String street;
	
	@NotBlank(message = "city is required")
	private String city;
	
	@NotBlank(message = "state is required")
	private String state;
	
	@NotBlank(message = "country is required")
	private String Country;
	
	@NotNull(message = "pincode cannot be null")
	private String pincode;
}
