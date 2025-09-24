package com.hotelBookingSystem.hotelSystem.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hotelBookingSystem.hotelSystem.entity.User;
import com.hotelBookingSystem.hotelSystem.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String UserName) throws UsernameNotFoundException {
		User user = userRepo.findByUserName(UserName);
		if (user == null) {
			throw new UsernameNotFoundException("User not Found with:" + UserName);
		}
		return user;
	}

}
