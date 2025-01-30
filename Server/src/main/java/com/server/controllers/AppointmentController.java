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
import com.server.models.Appointment;
import com.server.services.AppointmentService;
import com.server.utils.DTOConvertor;

@RestController
@RequestMapping("/api")
public class AppointmentController {
	@Autowired
	AppointmentService appServ;
	
	@GetMapping("/appointments")
	public ResponseEntity<List<AppointmentDTO>> allApp(){
		List<AppointmentDTO> app=appServ.getAllApp().stream()
				.map(DTOConvertor::convertToAppDTO)
				.collect(Collectors.toList());
		return new ResponseEntity<>(app,HttpStatus.OK);
	}
	
	@GetMapping("/appointment/{id}")
	public ResponseEntity<AppointmentDTO> getApp(@PathVariable("id")Long id){
		AppointmentDTO app=DTOConvertor.convertToAppDTO(appServ.getOneApp(id));
		return new ResponseEntity<>(app,HttpStatus.OK);
	}
	
	
	@PostMapping (value="/appointment/create",consumes="application/json",produces="application/json")
	public ResponseEntity<AppointmentDTO> createApp(@RequestBody AppointmentDTO appDTO){
		Appointment app=DTOConvertor.convertTOAppointmentEntity(appDTO);
		Appointment createApp=appServ.createApp(app);
		AppointmentDTO createAppDTO=DTOConvertor.convertToAppDTO(createApp);
		return new ResponseEntity<>(createAppDTO,HttpStatus.CREATED);
	}
	
	
}
