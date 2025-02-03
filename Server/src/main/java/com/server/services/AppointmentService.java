package com.server.services;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.dtos.AppointmentDTO;
import com.server.models.Appointment;
import com.server.models.Patient;
import com.server.repositories.AppointmentRepo;
import com.server.repositories.PatientRepo;
import com.server.repositories.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentService {
	
	private final ModelMapper modelMapper;
	@Autowired
	AppointmentRepo appRepo;
	@Autowired
	JwtService jwtService;
	@Autowired
	UserRepository userRepository;
	@Autowired
	PatientRepo patientRepo;
	
	public List<AppointmentDTO> getAllApp(){
		return appRepo.findAll().stream()
				.map(this::convertToAppDTO)
				.collect(Collectors.toList());
	}
	
	public AppointmentDTO getOneApp(long id) {
		return appRepo.findById(id)
				.map(this::convertToAppDTO)
				.orElseThrow(() -> new RuntimeException("Appointment not found"));
	}
	
	public AppointmentDTO createApp(Appointment app,HttpServletRequest request) {
		String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            Long userId = jwtService.extractUserId(token);
            if (patientRepo.findByUserId(userId).isPresent()) {
                Patient patient = patientRepo.findByUserId(userId).get();
                app.setPatient(patient);
                return convertToAppDTO( appRepo.save(app));
            }
            throw new RuntimeException("Patient not found");
        }
        throw new RuntimeException("Authorization header is missing or invalid");
	}
	
	
	public void deleteApp(Long id) {
		AppointmentDTO appToDelete=getOneApp(id);
		appToDelete.setDoctor(null);
		appToDelete.setPatient(null);
		appRepo.deleteById(id);
	}
	
	public  AppointmentDTO convertToAppDTO(Appointment app) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelMapper.map(app, AppointmentDTO.class);
	}
}
