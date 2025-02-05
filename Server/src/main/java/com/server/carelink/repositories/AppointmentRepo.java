package com.server.carelink.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.server.carelink.models.Appointment;

@Repository
public interface AppointmentRepo extends CrudRepository<Appointment,Long> {
	
	List<Appointment> findAll();

}
