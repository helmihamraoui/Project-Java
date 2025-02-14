package com.server.carelink.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.server.carelink.models.Appointment;

@Repository
public interface AppointmentRepo extends CrudRepository<Appointment,Long> {
	@Query("SELECT a FROM Appointment a WHERE a.patient.id = :patientId ORDER BY a.createdAt DESC")
	List<Appointment> findByPatientId(Long patientId);
	List<Appointment> findAll();
	@Query("SELECT a FROM Appointment a WHERE a.doctor.id = :id")
	List<Appointment> findByDoctorId(@Param("id") Long id);


}
