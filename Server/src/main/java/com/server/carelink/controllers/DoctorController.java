package com.server.carelink.controllers;

import java.util.List;

import com.server.carelink.dtos.DoctorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.carelink.services.DoctorService;


@RestController
@RequestMapping("/api/v1/any")
public class DoctorController {

	@Autowired
	DoctorService doctorService;
	
	@GetMapping("/doctors")
	public ResponseEntity<List<DoctorDTO>> AllDoctors(){
		List<DoctorDTO> doctors=doctorService.getAllDoctors();
		return ResponseEntity.ok(doctors);
	}
	
	@GetMapping("/doctors/{id}")
	public ResponseEntity<DoctorDTO> getDoctor(@PathVariable("id")Long id) {
		DoctorDTO doctor=doctorService.getOneDoctor(id);
		return ResponseEntity.ok(doctor);
	}
	
	@PostMapping("/doctors/new")
	public ResponseEntity<DoctorDTO> addDoctor(@RequestBody DoctorDTO doctorDTO){
		DoctorDTO created=doctorService.addDoctor(doctorDTO);
		return ResponseEntity.ok(created);
	}
	
	
	
}
