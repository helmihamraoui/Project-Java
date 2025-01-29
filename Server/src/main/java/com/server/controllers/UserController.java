package com.server.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.dtos.UserDTO;
import com.server.models.Role;
import com.server.services.UserService;

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
	
	
 
	
}
