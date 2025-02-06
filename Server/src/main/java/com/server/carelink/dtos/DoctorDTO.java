package com.server.carelink.dtos;

import java.util.List;

import com.server.carelink.models.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorDTO {
	private Long id;
	private String specialties;
	private String jobTitle;
	private Integer licenseNumb;
	private Integer experience;
	private User user;
	
	private List<AppointmentDTOWithoutDoctor> appointments;
	

	
	
}
