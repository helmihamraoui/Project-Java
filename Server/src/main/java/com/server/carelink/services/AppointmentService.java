package com.server.carelink.services;


import java.util.List;
import java.util.stream.Collectors;

import com.server.carelink.models.Doctor;
import com.server.carelink.repositories.DoctorRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.carelink.dtos.AppointmentDTO;
import com.server.carelink.models.Appointment;
import com.server.carelink.models.Patient;
import com.server.carelink.repositories.AppointmentRepo;
import com.server.carelink.repositories.PatientRepo;
import com.server.carelink.repositories.UserRepository;

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
	@Autowired
	DoctorRepo doctorRepo;
	public List<AppointmentDTO> getAllApp(){
		return appRepo.findAll().stream()
				.map(this::convertToAppDTO)
				.collect(Collectors.toList());
	}
	public List<AppointmentDTO> getAllAppofPatient(Long id){
		return appRepo.findByPatientId(id).stream()
				.map(this::convertToAppDTO)
				.collect(Collectors.toList());
	}
	public AppointmentDTO getOneApp(long id) {
		return appRepo.findById(id)
				.map(this::convertToAppDTO)
				.orElseThrow(() -> new RuntimeException("Appointment not found"));
	}
	public AppointmentDTO addApp(AppointmentDTO app){
		Appointment appointment = convertToAppEntity(app);
		Appointment savedapp=appRepo.save(appointment);
		return convertToAppDTO(savedapp);
	}
	public List<AppointmentDTO> findByDoctorId(Long doctorId) {
		// Fetching the appointments from the repository
		List<Appointment> appointments = appRepo.findByDoctorId(doctorId);

		// Converting the entities to DTOs
		return appointments.stream()
				.map(this::convertToAppDTO)  // Convert each Appointment to AppointmentDTO
				.collect(Collectors.toList());
	}
	
	public AppointmentDTO deleteApp(Long id) {
		AppointmentDTO appToDelete=getOneApp(id);
		appToDelete.setDoctor(null);
		appToDelete.setPatient(null);
		appRepo.deleteById(id);
		return appToDelete;
	}
	
	public  AppointmentDTO convertToAppDTO(Appointment app) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelMapper.map(app, AppointmentDTO.class);
	}
	public Appointment convertToAppEntity(AppointmentDTO dto) {
		Appointment appointment = new Appointment();
		appointment.setPatient(new Patient());
		appointment.getPatient().setId(dto.getPatient().getId());

		appointment.setDoctor(new Doctor());
		appointment.getDoctor().setId(dto.getDoctor().getId());
		appointment.setTime(dto.getTime());
		return appointment;
	}

}
