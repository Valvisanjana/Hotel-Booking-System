package com.hotelBookingSystem.hotelSystem.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotelBookingSystem.hotelSystem.dto.PaymentDto;
import com.hotelBookingSystem.hotelSystem.service.PaymentService;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@PreAuthorize("hasRole('USER')")
	@PostMapping("/do/{bookingId}")
	public ResponseEntity<PaymentDto> dopayment(@RequestBody PaymentDto paymentDto, @PathVariable int bookingId) {
		return ResponseEntity.ok(paymentService.doPayment(paymentDto, bookingId));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/get/{bookingId}")
	public ResponseEntity<PaymentDto> getPaymentById(@PathVariable int bookingId) {
		return ResponseEntity.ok(paymentService.getPaymentByBookingId(bookingId));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/getAll")
	public ResponseEntity<List<PaymentDto>> getPayments() {
		return ResponseEntity.ok(paymentService.getAllPayments());
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/edit/{bookingId}/status")
	public ResponseEntity<String> dopayment(@PathVariable int bookingId, @RequestParam String status) {
		paymentService.updatePaymentStatus(bookingId, status);
		return ResponseEntity.ok("Payment status updated" + status);
	}

	@PreAuthorize("hasRole('USER')")
	@DeleteMapping("/delete/{bookingId}")
	public ResponseEntity<String> deletePaymentById(@PathVariable int bookingId) {
		paymentService.cancelPayment(bookingId);
		return ResponseEntity.ok("Payment cancelled successFully for booking Id:" + bookingId);
	}

}
