package com.server.carelink.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.carelink.dtos.DiagnosesDTO;
import com.server.carelink.dtos.DiagnosesWithIdDTO;
import com.server.carelink.services.DiagnoseService;
@RestController
@RequestMapping("/api/v1/any")
@CrossOrigin(origins = "http://localhost:4200")
public class DiagnoseController {
	@Autowired
	DiagnoseService diagnoseServ;
	
	
	@GetMapping("/diagnose/{id}")
	public DiagnosesDTO getDiagnose(@PathVariable("id")Long id) {
		DiagnosesDTO diagnose=diagnoseServ.getOne(id);
		return diagnose;
	}
	
	@PostMapping("/diagnose/new/{id}")
	public DiagnosesDTO newDiagnose(@RequestBody DiagnosesDTO diagnosesDTO,@PathVariable("id")long id) {
		DiagnosesDTO createdDiagnose=diagnoseServ.addDiagnose(diagnosesDTO,id);
		return createdDiagnose;
	}
	
	@PutMapping("/diagnose/add/{id}")
	public DiagnosesDTO addDiagnose(@PathVariable("id")Long id,@RequestBody DiagnosesWithIdDTO diagnoseDTO) {
		System.out.println(id);
		return diagnoseServ.editDiagnosis(id, diagnoseDTO);
	}
}
