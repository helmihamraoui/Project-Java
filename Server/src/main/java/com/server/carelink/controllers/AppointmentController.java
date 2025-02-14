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


		@GetMapping("/appointments/patient/{id}")
		public ResponseEntity<List<AppointmentDTO>> getAppointmentsByPatient(@PathVariable Long id) {
			List<AppointmentDTO> appointments = appServ.getAllAppofPatient(id);
			return ResponseEntity.ok(appointments);
	}

	@PostMapping ("/appointments/new")
	public ResponseEntity<AppointmentDTO> createApp(@RequestBody AppointmentDTO app){
		AppointmentDTO appDTO= appServ.addApp(app);
				return ResponseEntity.ok(appDTO);
	}
	@GetMapping("/appointments/doctor/{id}")
	public ResponseEntity<List<AppointmentDTO>> getAppointementsOfDoctor(@PathVariable Long id){
		List<AppointmentDTO> appointments = appServ.findByDoctorId(id);
		return ResponseEntity.ok(appointments);
	}
	@DeleteMapping("/delete/appointments/{id}")
	public ResponseEntity<AppointmentDTO> deleteAppointment(@PathVariable Long id) {
		AppointmentDTO app = appServ.deleteApp(id);
		if (app != null) {
			return ResponseEntity.ok(app); // Return 200 OK with deleted appointment data
		} else {
			return ResponseEntity.notFound().build(); // Return 404 if appointment not found
		}
	}

	
}
