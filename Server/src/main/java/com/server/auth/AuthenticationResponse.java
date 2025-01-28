package com.server.auth;


import com.server.models.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

	private Long id;
	private String token;
  private String firstname;
  private String lastname;
  private String email;
  
  
  
  private Role role;
}
