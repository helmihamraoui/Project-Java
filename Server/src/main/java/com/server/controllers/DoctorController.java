package com.server.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.dtos.AppointmentDTO;
import com.server.dtos.DoctorDTO;
import com.server.models.Appointment;
import com.server.services.DoctorService;
import com.server.utils.DTOConvertor;


@RestController
@RequestMapping("/api")
public class DoctorController {

	@Autowired
	DoctorService doctorService;
	
	@GetMapping("/doctors")
	public ResponseEntity<List<DoctorDTO>> AllDoctors(){
		List<DoctorDTO> doctors=doctorService.getAllDoctors().stream()
				.map(DTOConvertor::convertToDoctorDTO)
				.collect(Collectors.toList());
		return new ResponseEntity<>(doctors,HttpStatus.OK);
	}
	
	@GetMapping("/doctors/{id}")
	public ResponseEntity<DoctorDTO> getDoctor(@PathVariable("id")Long id) {
		DoctorDTO doctor=DTOConvertor.convertToDoctorDTO(doctorService.getOneDoctor(id));
		return new ResponseEntity<>(doctor,HttpStatus.OK);
	}
	
	
	
}
