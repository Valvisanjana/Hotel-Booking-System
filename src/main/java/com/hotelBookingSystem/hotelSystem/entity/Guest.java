package com.hotelBookingSystem.hotelSystem.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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

	@OneToOne(mappedBy = "guest", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Address address;

	@OneToMany(mappedBy = "guest", fetch = FetchType.EAGER)
	private List<Booking> bookings;
}
