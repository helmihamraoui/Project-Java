package com.server.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import com.server.dtos.UserDTO;
import com.server.models.Role;
import com.server.models.User;
import com.server.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService { 
	private final  UserRepository userRepo;  
    private final ModelMapper modelMapper;

	  public List<UserDTO> getAllUsers() {
	        return userRepo.findAll().stream()
	                .map(this::convertEntityToDto)
	                .collect(Collectors.toList());
	    } 
	  
	 
	  public List<UserDTO> getUsersByRole(Role role) {
	        return userRepo.findByRole(role).stream()
	                .map(this::convertEntityToDto)
	                .collect(Collectors.toList());
	    }
	  
	  
	  public User convertDtoToEntity(UserDTO userDTO) {
	        return modelMapper.map(userDTO, User.class);
	    }
	  
	  public UserDTO convertEntityToDto(User user) {
	        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
	        return modelMapper.map(user, UserDTO.class);
	    }
	  
 
	 
}
