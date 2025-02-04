package com.server.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AvailabilityDTO {
	
	private Long id;
	private String day;
	private String startTime;
	private String endTime;
	private DoctorDTO doctor;
}
