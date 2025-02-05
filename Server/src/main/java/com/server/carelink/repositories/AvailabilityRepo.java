package com.server.carelink.repositories;

import java.util.List;
import java.util.Optional;

import com.server.carelink.models.Availability;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailabilityRepo extends CrudRepository<Availability,Long> {

	List<Availability> findAll();
	
	@Query("SELECT a FROM Availability a WHERE a.doctor.id= ?1")
	Optional<Availability> findByDoctorId(Long id);
}
