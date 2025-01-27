package com.server.utils;

import com.server.dtos.DoctorDTO;
import com.server.models.Doctor;

public class DTOConvertor {
	
	public Static DoctorDTO convertToDoctorDTO(Doctor doc) {
		DoctorDTO doctorDTO=new DoctorDTO();
		doctorDTO.setId(doc.getId());
		doctorDTO.setSpecialties(doc.getSpecialties());
		doctorDTO.setJobTitle(doc.getJobTitle());
		doctorDTO.setLicenseNumb(doc.getLicenseNumb());
		doctorDTO.setExperience(doc.getExperience());
		return doctorDTO;
	}
}
