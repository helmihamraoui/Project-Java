package com.server.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.catalina.realm.JNDIRealm.User;
import org.springframework.beans.factory.annotation.Autowired;

import com.server.dtos.DoctorDTO;
import com.server.dtos.PatientDTO;
import com.server.models.Doctor;
import com.server.models.Patient;
import com.server.repositories.UserRepository;

public class DTOConvertor {
	@Autowired
    private static UserRepository userRepository;
	public static DoctorDTO convertToDoctorDTO(Doctor doc) {
		DoctorDTO doctorDTO=new DoctorDTO();
		doctorDTO.setId(doc.getId());
		doctorDTO.setSpecialties(doc.getSpecialties());
		doctorDTO.setJobTitle(doc.getJobTitle());
		doctorDTO.setLicenseNumb(doc.getLicenseNumb());
		doctorDTO.setExperience(doc.getExperience());
		if(ConverToUserDTO(doc.getUser())!=null) {
			doctorDTO.setUser(ConverToUserDTO(doc.getUser()));
		}
		return doctorDTO;
	}
	
	public static Doctor convertToDoctorEntity(DoctorDTO docDTO) {
		Doctor doc=new Doctor();
		doc.setId(docDTO.getId());
		doc.setSpecialties(docDTO.getSpecialties());
		doc.setJobTitle(docDTO.getJobTitle());
		doc.setLicenseNumb(docDTO.getLicenseNumb());
		doc.setExperience(docDTO.getExperience());
		return doc;
		
	}
    // Convert Patient entity to PatientDTO
    public static PatientDTO toDTO(Patient patient) {
        if (patient == null) {
            return null;
        }
        return new PatientDTO(
            patient.getId(),
            patient.getEmergContact(), // Fixed property naming convention
            patient.getUser() != null ? patient.getUser().getId() : null
        );
    }

    // Map a list of Patient entities to a list of PatientDTOs
    public static List<PatientDTO> toDTOList(List<Patient> patients) {
        return patients.stream()
                       .map(DTOConvertor::toDTO) // Fixed to call the method within the same class
                       .collect(Collectors.toList());
    }

    // Convert PatientDTO to Patient entity
    public static Patient toEntity(PatientDTO patientDTO, UserRepository userRepository) {
        if (patientDTO == null) {
            return null;
        }

        Patient patient = new Patient();
        patient.setId(patientDTO.getId());
        patient.setEmergContact(patientDTO.getEmergContact()); // Fixed property naming convention

        // Handle the mapping of User (if userId exists)
        if (patientDTO.getUserId() != null) {
            User user = userRepository.findById(patientDTO.getUserId())
                                       .orElseThrow(() -> new RuntimeException("User not found with ID: " + patientDTO.getUserId()));
            patient.setUser(user);
        }

        return patient;
    }

}
