package com.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.dtos.PatientDTO;
import com.server.models.Patient;
import com.server.repositories.PatientRepo;
import com.server.utils.DTOConvertor;

@Service
public class PatientService {

    @Autowired
    private PatientRepo patientRpo;

    // Get all patients and convert to PatientDTO
    public List<PatientDTO> getAllPatients() {
        List<Patient> patients = patientRpo.findAll();
        return DTOConvertor.toDTOList(patients);
    }

    // Add a new patient
    public PatientDTO addPatient(PatientDTO patientDTO) {
        Patient patient = DTOConvertor.toEntity(patientDTO); // Convert DTO to entity
        Patient savedPatient = patientRpo.save(patient); // Save entity in database
        return DTOConvertor.toDTO(savedPatient); // Convert saved entity to DTO and return
    }
}
