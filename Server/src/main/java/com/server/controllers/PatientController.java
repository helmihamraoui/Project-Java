package com.server.controllers;

import com.server.dtos.PatientDTO;
import com.server.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PatientController {
    @Autowired
    private PatientService patientService;
    // GET endpoint to retrieve all patients
    @GetMapping("/patient")
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        List<PatientDTO> patients = patientService.getAllPatients();
        return ResponseEntity.ok(patients);
    }
    // POST endpoint to add a new patient
    @PostMapping("/patient/new")
    public ResponseEntity<PatientDTO> addPatient(@RequestBody PatientDTO patientDTO) {
        PatientDTO createdPatient = patientService.addPatient(patientDTO);
        return ResponseEntity.ok(createdPatient);
    }
}
