package com.server.carelink.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.server.carelink.models.Doctor;

@Repository
public interface DoctorRepo extends CrudRepository<Doctor,Long> {
	
	List<Doctor> findAll();
	
	@Query("SELECT d FROM Doctor d WHERE d.user.id= ?1")
	Optional<Doctor> findByUserId(Long id);

}