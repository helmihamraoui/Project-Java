package com.server.controllers;

import com.server.dtos.PatientDTO;
import com.server.services.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    // GET endpoint to retrieve all patients
    @GetMapping("/any/patient/all")
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        List<PatientDTO> patients = patientService.getAllPatients();
        return ResponseEntity.ok(patients);
    }

    // GET endpoint to retrieve a single patient by ID
    @GetMapping("/any/patient/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable Long id) {
        PatientDTO patient = patientService.getPatientById(id);
        return ResponseEntity.ok(patient);
    }

    // POST endpoint to add a new patient
    @PostMapping("/any/patient/new")
    public ResponseEntity<PatientDTO> addPatient(@RequestBody PatientDTO patientDTO){
        PatientDTO createdPatient = patientService.addPatient(patientDTO);
        return ResponseEntity.ok(createdPatient);
    }
}
