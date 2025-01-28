package com.server.auth;

import java.util.Date;

import com.server.models.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  private String firstname;
  private String lastname;
  private String email;
  private String password; 
  private String confirm;
  private Date date; 
  private String image; 
  private String number; 
  private String address;  
  
  
  private Role role;
}