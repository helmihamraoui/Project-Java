package com.server.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class DoctorDTOApp {
	private Long id;
	private String specialties;
	private String jobTitle;
	private Integer licenseNumb;
	private Integer experience;
	private UserDTO user;
	
	
	
}
