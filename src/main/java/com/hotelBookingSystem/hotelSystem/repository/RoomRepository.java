package com.hotelBookingSystem.hotelSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotelBookingSystem.hotelSystem.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

	Room findByRoomNumber(String roomNumber);

	Room findByRoomType(String roomType);

}
