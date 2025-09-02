package com.hotelBookingSystem.hotelSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
     private String UserName;
     private String email;
     private String password;
     private String role;
}
 