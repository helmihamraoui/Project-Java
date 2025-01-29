package com.server.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.server.models.Doctor;

@Repository
public interface DoctorRepo extends CrudRepository<Doctor,Long> {
	
	List<Doctor> findAll();

}