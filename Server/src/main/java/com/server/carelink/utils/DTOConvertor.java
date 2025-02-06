package com.server.carelink.utils;

import com.server.carelink.dtos.AppointmentDTO;
import com.server.carelink.models.Appointment;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;


public class DTOConvertor {
	@Autowired
	private static ModelMapper modelMapper;
	
	
	
	
	
	
    
	public static Appointment convertTOAppointmentEntity(AppointmentDTO appDTO) {
		return modelMapper.map(appDTO, Appointment.class);
	}
	
}
