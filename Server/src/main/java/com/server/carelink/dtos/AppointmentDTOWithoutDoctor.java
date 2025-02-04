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
public class AppointmentDTOWithoutDoctor {
	private Long id;
	private Date date;
	private PatientDTO patient;

}
