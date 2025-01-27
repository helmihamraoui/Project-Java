package com.server.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;pping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.dtos.DoctorDTO;
import com.server.models.Doctor;
import com.server.services.DoctorService;


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
	
	}
	
	@GetMapping("/doctors/{id}")
	public Doctor getDoctor(@PathVariable("id")Long id) {
		
		return doctorService.getOneDoctor(id);
	}
	
	@PutMapping("/api/doctors/{id}")
	public Doctor UpdateDoctor (@PathVariable("id")Long id
									) {
		
	}
}
