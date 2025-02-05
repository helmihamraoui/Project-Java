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

public class AppointmentDTO {

	private Long id;
	private String date;
	private DoctorDTOApp doctor;
	private PatientDTO patient;
	
	
	
	
	
}
