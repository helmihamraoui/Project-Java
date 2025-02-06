package com.server.carelink.auth;

import com.server.carelink.dtos.PatientDTO;
import com.server.carelink.dtos.UserDTO;
import com.server.carelink.models.Message;
import com.server.carelink.services.MessageService;
import com.server.carelink.services.PatientService;
import com.server.carelink.services.UserService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
	private final AuthenticationService service;
	private final PatientService patientService;
	private final UserService userService;
	private final MessageService messageService;
	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(
			@RequestBody RegisterRequest request
	) {
		return ResponseEntity.ok(service.register(request));
	}
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(
			@RequestBody AuthenticationRequest request
	) {
		return ResponseEntity.ok(service.authenticate(request));
	}
	@PostMapping("/any/patient/new/{id}")
	public ResponseEntity<PatientDTO> addPatient(@RequestBody PatientDTO patientDTO, @PathVariable("id")Long id){
		UserDTO user=userService.getUserById(id);
		patientDTO.setUser(user);
		PatientDTO createdPatient = patientService.addPatient(patientDTO);
		return ResponseEntity.ok(createdPatient);
	}
	@GetMapping("/any/patient/all")
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        List<PatientDTO> patients = patientService.getAllPatients();
        return ResponseEntity.ok(patients);
    }

}