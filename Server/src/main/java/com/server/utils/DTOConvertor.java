package com.server.utils;

import org.springframework.beans.factory.annotation.Autowired;
import com.server.dtos.DoctorDTO;
import com.server.models.Doctor;
import com.server.services.UserService;

public class DTOConvertor {
	@Autowired
	private static UserService userService;
	
	public static DoctorDTO convertToDoctorDTO(Doctor doc) {
		DoctorDTO doctorDTO=new DoctorDTO();
		doctorDTO.setId(doc.getId());
		doctorDTO.setSpecialties(doc.getSpecialties());
		doctorDTO.setJobTitle(doc.getJobTitle());
		doctorDTO.setLicenseNumb(doc.getLicenseNumb());
		doctorDTO.setExperience(doc.getExperience());
		if(userService.convertEntityToDto(doc.getUser())!=null) {
			doctorDTO.setUser(userService.convertEntityToDto(doc.getUser()));
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
