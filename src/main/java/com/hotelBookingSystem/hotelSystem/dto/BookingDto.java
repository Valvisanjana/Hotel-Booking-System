package com.hotelBookingSystem.hotelSystem.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {
	private int bookingId;
	
	@NotBlank(message = "guest Id is required")
	private int guestId;
	
	private int roomId;
	private LocalDateTime check_in_date;
	private LocalDateTime check_out_date;
	private double total_amount;
	private String status;
}
