package com.hotelBookingSystem.hotelSystem.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuestDto {

	private int guestId;
	private String guestName;
	private String email;
	private String phone_Number;
	private String id_Proof;
	private LocalDateTime check_in_date;
	private LocalDateTime check_out_date;
}
