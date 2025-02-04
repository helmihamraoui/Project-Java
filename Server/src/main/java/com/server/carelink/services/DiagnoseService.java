package com.server.carelink.services;

import com.server.carelink.dtos.DiagnosesDTO;
import com.server.carelink.repositories.DiagnosesRepo;

import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

import com.server.carelink.models.Diagnoses;

@Service
@RequiredArgsConstructor
public class DiagnoseService {

    private final DiagnosesRepo diagnosisRepo;
    private final ModelMapper modelMapper;

    // Get one diagnosis by ID
    public Optional<Diagnoses> getOne(Long id) {
        return diagnosisRepo.findById(id);
    }

    // Edit an existing diagnosis
    public Diagnoses editDiagnosis(Long id, DiagnosesDTO diagnosesDTO) {
        return diagnosisRepo.findById(id).map(existingDiagnosis -> {
            existingDiagnosis.setTreatments(diagnosesDTO.getTreatments());
            existingDiagnosis.setAllergies(diagnosesDTO.getAllergies());
            existingDiagnosis.setDoctors(diagnosesDTO.getDoctors());  // Directly set the list of doctor IDs
            return diagnosisRepo.save(existingDiagnosis);
        }).orElseThrow(() -> new RuntimeException("Diagnosis not found"));
    }

    // Convert DTO to Entity
    public Diagnoses convertToEntity(DiagnosesDTO diagnosesDTO) {
        Diagnoses diagnosis = modelMapper.map(diagnosesDTO, Diagnoses.class);
        diagnosis.setDoctors(diagnosesDTO.getDoctors());  // Directly set the list of doctor IDs
        return diagnosis;
    }

    // Convert Entity to DTO
    public DiagnosesDTO convertToDto(Diagnoses diagnosis) {
        DiagnosesDTO diagnosesDTO = modelMapper.map(diagnosis, DiagnosesDTO.class);
        diagnosesDTO.setDoctors(diagnosis.getDoctors());  // Set the list of doctor IDs
        return diagnosesDTO;
    }
}
