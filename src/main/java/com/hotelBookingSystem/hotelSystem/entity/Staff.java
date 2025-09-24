package com.hotelBookingSystem.hotelSystem.entity;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Staff {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int staffId;
	private String staffName;
	private String email;
	private String phone_Number;
	private String role;
	private LocalDate joiningDate;

	@OneToOne(mappedBy = "staff", cascade = CascadeType.ALL)
	private Address address;

	@ManyToOne
	@JoinColumn(name = "hotel_id")
	private Hotel hotel;

}
