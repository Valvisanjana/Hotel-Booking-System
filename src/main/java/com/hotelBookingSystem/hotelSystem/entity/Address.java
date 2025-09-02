package com.hotelBookingSystem.hotelSystem.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor 
@Entity
public class Address {
	 @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int addressId;
	 private String street;
	 private String city;
	 private String state;
	 private String Country; 
	 private String pincode;
	 
	 @OneToMany(mappedBy = "address", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	 private List<Guest> guest; 
	 
	 @OneToOne
	 @JoinColumn(name = "staff_id")
	 private Staff staff;
}
