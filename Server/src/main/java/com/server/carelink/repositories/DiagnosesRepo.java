package com.server.carelink.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.carelink.models.Diagnoses;

public interface DiagnosesRepo extends JpaRepository<Diagnoses, Long>{
    
}
