package com.hotelBookingSystem.hotelSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotelBookingSystem.hotelSystem.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByEmail(String email);

	User findByUserName(String username);

}
