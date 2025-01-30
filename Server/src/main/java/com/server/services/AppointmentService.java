package com.server.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.models.Appointment;
import com.server.repositories.AppointmentRepo;

@Service
public class AppointmentService {

	@Autowired
	AppointmentRepo appRepo;
	
	public List<Appointment> getAllApp(){
		return appRepo.findAll();
	}
	
	public Appointment getOneApp(long id) {
		Optional<Appointment> a=appRepo.findById(id);
		if(a.isEmpty()) {
			return null;
		}
		return a.get();
	}
	
	public Appointment createApp(Appointment app) {
		return appRepo.save(app);
	}
	
	public Appointment updateApp(Appointment app,Long id) {
		app.setId(id);
		return appRepo.save(app);
	}
	
	public void deleteApp(Long id) {
		Appointment appToDelete=getOneApp(id);
		appToDelete.setDoctor(null);
		appToDelete.setPatient(null);
		appRepo.deleteById(id);
	}
}
