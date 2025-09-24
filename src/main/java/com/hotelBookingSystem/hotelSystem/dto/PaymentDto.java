package com.hotelBookingSystem.hotelSystem.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
	private int paymentId;
	private double amount;
	private LocalDateTime payment_date;
	private String payment_method;
	private String status;
	private int bookingId;
}
