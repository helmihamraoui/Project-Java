package com.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.models.Diagnoses;

public interface DiagnosesRepo extends JpaRepository<Diagnoses, Long>{
    
}
