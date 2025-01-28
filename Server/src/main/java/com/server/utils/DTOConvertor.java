package com.server.utils;

import com.server.dtos.DoctorDTO;
import com.server.models.Doctor;

public class DTOConvertor {
	
	public static DoctorDTO convertToDoctorDTO(Doctor doc) {
		DoctorDTO doctorDTO=new DoctorDTO();
		doctorDTO.setId(doc.getId());
		doctorDTO.setSpecialties(doc.getSpecialties());
		doctorDTO.setJobTitle(doc.getJobTitle());
		doctorDTO.setLicenseNumb(doc.getLicenseNumb());
		doctorDTO.setExperience(doc.getExperience());
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
