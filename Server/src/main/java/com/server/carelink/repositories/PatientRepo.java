package com.server.carelink.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.server.carelink.models.Patient;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Long> {
    // No need for custom methods for finding all patients
	@Query("SELECT p FROM Patient p WHERE p.user.id= ?1")
	Optional<Patient> findByUserId(Long id);
}
