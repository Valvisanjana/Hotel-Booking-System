package com.hotelBookingSystem.hotelSystem.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
	private int paymentId;
	
	@NotBlank(message = "amount cannot be blank")
	private double amount;
	private LocalDateTime payment_date;
	private String payment_method;
	
	@NotEmpty(message = " status cannot be empty")
	private String status;
	private int bookingId;
}
