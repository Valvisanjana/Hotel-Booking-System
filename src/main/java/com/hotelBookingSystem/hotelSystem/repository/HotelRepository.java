package com.hotelBookingSystem.hotelSystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotelBookingSystem.hotelSystem.entity.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {

	Optional<Hotel> findByHotelName(String hotelName);

}
