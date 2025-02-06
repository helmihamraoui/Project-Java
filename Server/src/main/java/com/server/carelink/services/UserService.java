package com.server.carelink.services;

import java.util.List;
import java.util.stream.Collectors;

import com.server.carelink.dtos.UserDTO;
import com.server.carelink.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import com.server.carelink.models.Role;
import com.server.carelink.models.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService { 
	private final UserRepository userRepo;
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
	public UserDTO getUserById(Long id) {
		return userRepo.findById(id)
				.map(this::convertEntityToDto)
				.orElseThrow(() -> new RuntimeException("Patient not found"));
	}
	public User getUserById1(Long id) {
		return userRepo.findById(id).get();
	}



}
