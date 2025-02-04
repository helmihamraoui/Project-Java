package com.server.carelink.dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO { 
	private Long id; 
	private String firstname;
	  private String lastname;
	  private String email;
	  private String password; 
	  private String confirm;
	  private Date date; 
	  private String image; 
	  private String number; 
	  private String address; 
	  
}
