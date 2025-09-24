package com.hotelBookingSystem.hotelSystem.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelBookingSystem.hotelSystem.Exception.BookingNotFoundException;
import com.hotelBookingSystem.hotelSystem.dto.PaymentDto;
import com.hotelBookingSystem.hotelSystem.entity.Booking;
import com.hotelBookingSystem.hotelSystem.entity.Payment;
import com.hotelBookingSystem.hotelSystem.repository.BookingRepository;
import com.hotelBookingSystem.hotelSystem.repository.PaymentRepository;
import com.hotelBookingSystem.hotelSystem.service.PaymentService;

import jakarta.transaction.Transactional;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepo;

	@Autowired
	private BookingRepository bookingRepo;

	@Autowired
	private ModelMapper modelMap;

	private Payment convertToEntity(PaymentDto paymentDto) {
		return modelMap.map(paymentDto, Payment.class);
	}

	private PaymentDto convertToDto(Payment payment) {
		return modelMap.map(payment, PaymentDto.class);
	}

	@Override
	@Transactional
	public PaymentDto doPayment(PaymentDto paymentDto, int bookingId) {
		Booking booking = bookingRepo.findById(bookingId)
				.orElseThrow(() -> new BookingNotFoundException("Booking Not Found with Room Id :" + bookingId));

		if (booking.getPayment() != null) {
			throw new RuntimeException("Payment already done for Booking ID: " + bookingId);
		}

		Payment payment = convertToEntity(paymentDto);
		payment.setBooking(booking);

		Payment savedPayment = paymentRepo.save(payment);
		booking.setPayment(savedPayment);
		bookingRepo.save(booking);
		return convertToDto(savedPayment);
	}

	@Override
	public PaymentDto getPaymentByBookingId(int bookingId) {
		Booking booking = bookingRepo.findById(bookingId)
				.orElseThrow(() -> new BookingNotFoundException("Booking Not Found with Booking Id :" + bookingId));

		Payment payment = booking.getPayment();
		if (payment == null) {
			throw new RuntimeException("No payment found for Booking Id: " + bookingId);
		}
		return convertToDto(payment);
	}

	@Override
	public List<PaymentDto> getAllPayments() {
		List<Payment> payment = paymentRepo.findAll();
		return payment.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	@Override
	public void updatePaymentStatus(int bookingId, String status) {
		Booking booking = bookingRepo.findById(bookingId)
				.orElseThrow(() -> new BookingNotFoundException("Booking Not Found with Booking Id :" + bookingId));
		Payment payment = booking.getPayment();
		if (payment == null) {
			throw new RuntimeException("No payment found for Booking Id: " + bookingId);
		}
		payment.setStatus(status);
		paymentRepo.save(payment);

	}

	@Override
	@Transactional
	public String cancelPayment(int bookingId) {
		Booking booking = bookingRepo.findById(bookingId)
				.orElseThrow(() -> new BookingNotFoundException("Booking Not Found with Booking Id :" + bookingId));

		Payment payment = booking.getPayment();
		if (payment == null) {
			return "No payment found for Booking Id: " + bookingId;
		}

		payment.setStatus("CANCELLED");
		paymentRepo.save(payment);

		return "Payment cancelled successfully for Booking Id: " + bookingId;
	}

}
