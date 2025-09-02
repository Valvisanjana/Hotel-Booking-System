package com.hotelBookingSystem.hotelSystem.entity;

import java.time.LocalDateTime;
import java.util.List;

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
public class Guest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int guestId;
	private String guestName;
	private String email;
	private String phone_Number;
	private String id_Proof;
	private LocalDateTime check_in_date;
	private LocalDateTime check_out_date; 

	@ManyToOne
    @JoinColumn(name = "address_id") 
	private Address address;
	
	@OneToMany(mappedBy = "guest", fetch = FetchType.EAGER)
	private List<Booking> bookings;
}
