package com.hotelBookingSystem.hotelSystem.service;

import java.util.List;

import com.hotelBookingSystem.hotelSystem.dto.PaymentDto;

public interface PaymentService {

	PaymentDto doPayment(PaymentDto paymentDto, int bookingId);
	
    PaymentDto getPaymentByBookingId(int bookingId);

    List<PaymentDto> getAllPayments();

    void updatePaymentStatus(int bookingId, String status);

    String cancelPayment(int bookingId);
	
}
