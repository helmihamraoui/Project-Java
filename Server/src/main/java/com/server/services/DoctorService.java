package com.server.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.models.Doctor;
import com.server.repositories.DoctorRepo;

@Service
public class DoctorService {

	@Autowired
	DoctorRepo doctorRepo;
	
	public List<Doctor> getAllDoctors(){
		return doctorRepo.findAll();
	}
	
	public Doctor getOneDoctor(Long id) {
		Optional<Doctor> d=doctorRepo.findById(id);
		if(d.isEmpty()) {
			return null;
		}
		return d.get();
	}
	
	public Doctor updateDoctor(Doctor newDoc,Long id) {
		newDoc.setId(id);
		return doctorRepo.save(newDoc);
	}
	
	public Doctor createDoctor(Doctor doc) {
		return doctorRepo.save(doc);
	}
	
	public void deleteDoctor(Long id) {
		doctorRepo.deleteById(id);
	}
	
}
