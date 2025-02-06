package com.server.carelink.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.server.carelink.models.Appointment;

@Repository
public interface AppointmentRepo extends CrudRepository<Appointment,Long> {
	
	List<Appointment> findAll();
	@Query("SELECT a FROM Appointment a WHERE a.doctor.id= a.id")
	Optional<List<Appointment>> findByDoctorId(Long id);

}
