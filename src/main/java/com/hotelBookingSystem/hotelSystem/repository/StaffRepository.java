package com.hotelBookingSystem.hotelSystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotelBookingSystem.hotelSystem.entity.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer>{

	Optional<Staff> findBystaffName(String staffName);

	List<Staff> findByHotel_hotelName(String hotelName);


}
