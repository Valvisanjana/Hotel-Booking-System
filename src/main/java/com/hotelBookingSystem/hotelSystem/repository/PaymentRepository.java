package com.hotelBookingSystem.hotelSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotelBookingSystem.hotelSystem.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer>{

}
