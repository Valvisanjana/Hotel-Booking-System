package com.hotelBookingSystem.hotelSystem.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {
	private int bookingId;
	private int guestId;
	private int roomId;
	private LocalDateTime check_in_date;
	private LocalDateTime check_out_date;
	private double total_amount;
	private String status;
}
