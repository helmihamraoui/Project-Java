package com.server.carelink.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.carelink.dtos.DoctorDTO;
import com.server.carelink.models.User;
import com.server.carelink.services.DoctorService;
import com.server.carelink.services.UserService;

@RestController
@RequestMapping("/api/v1/any")
public class DoctorController {

	@Autowired
	DoctorService doctorService;
	@Autowired
	UserService userServ;
	
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
	
	@GetMapping("/doctor/{id}")
	public ResponseEntity<DoctorDTO>getByUserId(@PathVariable("id")Long id){
		DoctorDTO doctor=doctorService.getByUserId(id);
		return ResponseEntity.ok(doctor);
	}
	
	@PostMapping("/doctors/new/{id}")
	public ResponseEntity<DoctorDTO> addDoctor(@RequestBody DoctorDTO doctorDTO,@PathVariable("id")Long id){
		System.out.print("testtt");
		User user=userServ.getUserById1(id);
		System.out.print(user.getFirstName());
		doctorDTO.setUser(user);
		DoctorDTO created=doctorService.addDoctor(doctorDTO);
		return ResponseEntity.ok(created);
	}
	
	
	
}
