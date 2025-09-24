package com.hotelBookingSystem.hotelSystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotelBookingSystem.hotelSystem.entity.Guest;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Integer> {

	Optional<Guest> findByGuestName(String guestName);

	Optional<Guest> findByEmail(String email);

}
