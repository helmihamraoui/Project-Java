package com.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.dtos.AvailabilityDTO;
import com.server.models.Availability;
import com.server.services.AvailabilityService;

@RestController
@RequestMapping("/api/v1/any")
public class AvailabilityController {

	@Autowired
	AvailabilityService avServ;
	
	@PostMapping ("/availability/new/{id}")
	public ResponseEntity<AvailabilityDTO> createAv(@RequestBody Availability av, @PathVariable("id")Long id){
		AvailabilityDTO avDTO=avServ.createAv(av, id);
		return ResponseEntity.ok(avDTO);
	}
	
	@GetMapping ("availability/{id}")
	public ResponseEntity<AvailabilityDTO> getAv(@PathVariable("id")Long id ){
		AvailabilityDTO avDTO=avServ.getOneAv(id);
		return ResponseEntity.ok(avDTO);
	}
	
	
	
}
