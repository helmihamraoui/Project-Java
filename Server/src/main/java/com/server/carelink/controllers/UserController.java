package com.server.carelink.controllers;

import java.util.List;

import com.server.carelink.dtos.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.carelink.models.Role;
import com.server.carelink.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController { 
	private final UserService userservi;    
	
	
	
	
	  @GetMapping("/admin/user/all")
	    public List<UserDTO> getAllUsersDTO() {
	        return userservi.getAllUsers(); 
	        
	    } 
	  
	  
	  
	   @GetMapping("/posts/user/role/{role}")
	    public List<UserDTO> getUsersByRole(@PathVariable("role") Role role) {
	        return userservi.getUsersByRole(role); 
	    }
	
	
 		@GetMapping("/user/{id}")
			public ResponseEntity<UserDTO> getOneUser(@PathVariable("id")Long id){
		  		UserDTO user=userservi.getUserById(id);
				  return ResponseEntity.ok(user);
			}
	
}
