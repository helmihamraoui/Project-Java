package com.server.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.dtos.AppointmentDTO;
import com.server.models.Appointment;
import com.server.models.User;
import com.server.repositories.UserRepository;
import com.server.services.AppointmentService;
import com.server.services.JwtService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/any")
public class AppointmentController {
	@Autowired
	AppointmentService appServ;
	
	
	
	@GetMapping("/appointments")
	public ResponseEntity<List<AppointmentDTO>> allApp(){
		List<AppointmentDTO> app=appServ.getAllApp();
		return ResponseEntity.ok(app);
	}
	
	@GetMapping("/appointments/{id}")
	public ResponseEntity<AppointmentDTO> getApp(@PathVariable("id")Long id){
		AppointmentDTO app=appServ.getOneApp(id);
		return ResponseEntity.ok(app);
	}
	
	
	@PostMapping ("/appointments/new")
	public ResponseEntity<AppointmentDTO> createApp(@RequestBody Appointment app,HttpServletRequest request){
				AppointmentDTO appDTO= appServ.createApp(app, request);
				return ResponseEntity.ok(appDTO);
	}
	
	
}
