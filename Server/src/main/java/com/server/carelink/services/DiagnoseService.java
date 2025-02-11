package com.server.carelink.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.carelink.dtos.DiagnosesDTO;
import com.server.carelink.dtos.DiagnosesWithIdDTO;
import com.server.carelink.models.Diagnoses;
import com.server.carelink.models.Patient;
import com.server.carelink.repositories.DiagnosesRepo;
import com.server.carelink.repositories.PatientRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DiagnoseService {

    private final DiagnosesRepo diagnosisRepo;
    private final ModelMapper modelMapper;
    @Autowired
	PatientRepo patientRepo;

    // Get one diagnosis by ID
    public DiagnosesDTO getOne(Long id) {
        return diagnosisRepo.findById(id)
        		.map(this::convertToDto)
        		.orElseThrow(() -> new RuntimeException("Diagnose not found "));
    }

    // Edit an existing diagnosis
    public DiagnosesDTO editDiagnosis(Long id, DiagnosesWithIdDTO diagnosesDTO) {
        return diagnosisRepo.findById(id).map(existingDiagnosis -> {
        	System.out.println(diagnosesDTO.getDoctors());
            existingDiagnosis.setTreatments(existingDiagnosis.getTreatments()+" / " +diagnosesDTO.getTreatments());
            existingDiagnosis.setAllergies(existingDiagnosis.getAllergies()+" / " + diagnosesDTO.getAllergies());
            existingDiagnosis.getDoctors().add(diagnosesDTO.getDoctors());
            System.out.println(existingDiagnosis.getDoctors());
              // Directly set the list of doctor IDs
            Diagnoses saved=diagnosisRepo.save(existingDiagnosis);
            System.out.println(saved.getDoctors());
            return convertToDto( saved);
        }).orElseThrow(() -> new RuntimeException("Diagnosis not found"));
    }
    
    public DiagnosesDTO addDiagnose(DiagnosesDTO DiagnoseDTO,Long id) {
    	Diagnoses diagnose=convertToEntity(DiagnoseDTO);
    	Diagnoses savedDiagnose=diagnosisRepo.save(diagnose);
    	Patient patient=patientRepo.findById(id).get();
		patient.setDiagnose(savedDiagnose);
		patientRepo.save(patient);
    	return convertToDto(savedDiagnose);
    }

    // Convert DTO to Entity
    public Diagnoses convertToEntity(DiagnosesDTO diagnosesDTO ) {
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
