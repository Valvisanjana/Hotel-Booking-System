package com.hotelBookingSystem.hotelSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotelBookingSystem.hotelSystem.entity.Booking;
import com.hotelBookingSystem.hotelSystem.entity.Guest;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

	Booking findByGuest(Guest guest);

}
