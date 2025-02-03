package com.server.utils;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.server.dtos.AppointmentDTO;
import com.server.dtos.DoctorDTO;
import com.server.models.Appointment;
import com.server.models.Doctor;


public class DTOConvertor {
	@Autowired
	private static ModelMapper modelMapper;
	
	
	
	
	
	
    
	public static Appointment convertTOAppointmentEntity(AppointmentDTO appDTO) {
		return modelMapper.map(appDTO, Appointment.class);
	}
	
}
