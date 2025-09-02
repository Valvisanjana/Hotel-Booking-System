package com.hotelBookingSystem.hotelSystem.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookingId;
	private int roomId; 
	private LocalDateTime check_in_date;
	private LocalDateTime check_out_date;
	private double total_amount;
	private String status;
	
	@OneToMany(mappedBy = "booking",  cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Payment> payment;
	
	@ManyToOne
	@JoinColumn(name = "guest_id")
	private Guest guest;
	

}
