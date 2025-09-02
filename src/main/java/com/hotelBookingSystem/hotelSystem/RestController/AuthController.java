package com.hotelBookingSystem.hotelSystem.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelBookingSystem.hotelSystem.dto.LoginRequest;
import com.hotelBookingSystem.hotelSystem.dto.RegisterRequest;
import com.hotelBookingSystem.hotelSystem.entity.User;
import com.hotelBookingSystem.hotelSystem.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class AuthController {
	
	@Autowired
	private UserRepository userRepo; 
	
	@Autowired
	private AuthenticationProvider authenticationProvider;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/register")
	public String registerUser(@RequestBody RegisterRequest registerReq){
		if(userRepo.findByEmail(registerReq.getEmail()) != null) {
			throw new RuntimeException("User Email Already Exits!");
		}
		
		User user = new User();
		user.setUserName(registerReq.getUserName());
		user.setEmail(registerReq.getEmail());
	    user.setPassword(passwordEncoder.encode(registerReq.getPassword()));
	    
	    if (registerReq.getRole() != null) {
	    	user.setRole(registerReq.getRole());
	    } else {
	    	user.setRole("ROLE_USER");
	    }
	    userRepo.save(user);
		return "user registered successfully!"; 
	}
	
	@PostMapping("/login")
	public String userLogin(@RequestBody LoginRequest loginReq) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginReq.getUserName(),loginReq.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return "user login successfully!";
	}

}
