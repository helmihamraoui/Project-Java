package com.server.carelink.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.carelink.models.Role;
import com.server.carelink.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);  
  List<User> findByRole(Role role);
  Optional<User> findById(Long id);
 

}