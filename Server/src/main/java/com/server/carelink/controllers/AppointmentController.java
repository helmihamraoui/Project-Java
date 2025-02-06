package com.server.carelink.controllers;

import com.server.carelink.dtos.AppointmentDTO;
import com.server.carelink.models.Appointment;
import com.server.carelink.services.AppointmentService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/any")
@CrossOrigin(origins = "http://localhost:4200")
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
