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
    
}
