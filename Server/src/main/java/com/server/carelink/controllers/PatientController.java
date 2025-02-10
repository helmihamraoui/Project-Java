package com.server.carelink.controllers;

import com.server.carelink.dtos.PatientDTO;
import com.server.carelink.dtos.UserDTO;
import com.server.carelink.services.PatientService;
import com.server.carelink.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;
    private final UserService userService;
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
    @PostMapping("/any/patient/new/{id}")
    public ResponseEntity<PatientDTO> addPatient(@RequestBody PatientDTO patientDTO, @PathVariable("id")Long id){

        UserDTO user=userService.getUserById(id);
        patientDTO.setUser(user);
        PatientDTO createdPatient = patientService.addPatient(patientDTO);
        return ResponseEntity.ok(createdPatient);
    }
    @GetMapping("/patient/{userId}")
    public ResponseEntity<PatientDTO> getPatient(@PathVariable("userId")Long id){
        PatientDTO patient=patientService.getPatientByUserId(id);
        return ResponseEntity.ok(patient);
    }
}
