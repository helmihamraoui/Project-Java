package com.server.auth;

import com.server.dtos.PatientDTO;
import com.server.dtos.UserDTO;
import com.server.services.PatientService;
import com.server.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class AuthenticationController {
	private final AuthenticationService service;
	private final PatientService patientService;
	private final UserService userService;

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

}