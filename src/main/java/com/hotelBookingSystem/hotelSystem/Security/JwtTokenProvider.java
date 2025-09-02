package com.hotelBookingSystem.hotelSystem.Security;

import java.security.Key;
import java.util.Date;

import org.springframework.context.annotation.Configuration;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Configuration
public class JwtTokenProvider {

	private final String secret = "SuperSecretKey12345678901234567890";
    private final long EXPIRATION_TIME = 1000 * 60 * 60; 
	
	private Key getSinginKey() {
		return Keys.hmacShaKeyFor(secret.getBytes());
	}
	
	public String generateToken(String username) {
		return Jwts.builder()
				.setSubject(username) 
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(getSinginKey(),SignatureAlgorithm.HS256)
				.compact();
	}
	
	public String extractUserName(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(getSinginKey())
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getSubject(); 
	}
	
	public boolean validateToken(String token, String userName) {
		return userName.equals(extractUserName(token)) && !isTokenExpired(token);
	}

	private boolean isTokenExpired(String token) {
		Date expiration = Jwts.parserBuilder()
				.setSigningKey(getSinginKey())
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getExpiration();
				
		return expiration.before(new Date());
	}
}  
