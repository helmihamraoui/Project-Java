package com.server.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.dtos.DoctorDTO;
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
	
	public DoctorDTO convertToDoctorDTO(Doctor doc) {
		return modelMapper.map(doc, DoctorDTO.class);
	}
	
}
