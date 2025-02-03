package com.server.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.dtos.DoctorDTO;
import com.server.dtos.DoctorDTOApp;
import com.server.models.Doctor;
import com.server.repositories.DoctorRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DoctorService {
	private final ModelMapper modelMapper;
	@Autowired
	DoctorRepo doctorRepo;
	
	
	
	public List<DoctorDTO> getAllDoctors(){
		
		return doctorRepo.findAll().stream()
				.map(this::convertToDoctorDTO)
				.collect(Collectors.toList());
	}
	
	public DoctorDTO getOneDoctor(Long id) {
		return doctorRepo.findById(id)
				.map(this::convertToDoctorDTO)
				.orElseThrow(() ->new RuntimeException("Doctor not found"));
	}
	
	public Doctor updateDoctor(Doctor newDoc,Long id) {
		newDoc.setId(id);
		return doctorRepo.save(newDoc);
	}
	
	public void deleteDoctor(Long id) {
		doctorRepo.deleteById(id);
	}
	
	public DoctorDTO addDoctor(DoctorDTO doctorDTO) {
		Doctor doctor=convertToDoctorEntity(doctorDTO);
		Doctor savedDoctor=doctorRepo.save(doctor);
		return convertToDoctorDTO(savedDoctor);
	}
	
	public DoctorDTO convertToDoctorDTO(Doctor doc) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelMapper.map(doc, DoctorDTO.class);
	}
	
	public Doctor convertToDoctorEntity(DoctorDTO docDTO) {
		return modelMapper.map(docDTO, Doctor.class);
		
	}
	public DoctorDTOApp convertToDoctorDTOApp(Doctor doc) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelMapper.map(doc, DoctorDTOApp.class);
	}
	
	public Doctor convertToDoctorAppEntity(DoctorDTOApp docDTO) {
		return modelMapper.map(docDTO, Doctor.class);
		
	}
	
}
