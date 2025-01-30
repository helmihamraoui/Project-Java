package com.server.services;

import com.server.dtos.PatientDTO;
import com.server.models.Patient;
import com.server.repositories.PatientRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final ModelMapper modelMapper;
    private final PatientRepo patientRepo;

    // Get all patients
    public List<PatientDTO> getAllPatients() {
        return patientRepo.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    // Get patient by ID
    public PatientDTO getPatientById(Long id) {
        return patientRepo.findById(id)
                .map(this::convertEntityToDto)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    // Add a new patient
    public PatientDTO addPatient(PatientDTO patientDTO) {
        Patient patient = convertDtoToEntity(patientDTO);
        Patient savedPatient = patientRepo.save(patient);
        return convertEntityToDto(savedPatient);
    }

    // Convert Entity to DTO
    public PatientDTO convertEntityToDto(Patient patient) {
        return modelMapper.map(patient, PatientDTO.class);
    }

    // Convert DTO to Entity
    public Patient convertDtoToEntity(PatientDTO patientDTO) {
        return modelMapper.map(patientDTO, Patient.class);
    }
}
