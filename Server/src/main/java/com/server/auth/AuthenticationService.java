package com.server.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.server.models.Role;
import com.server.models.User;
import com.server.repositories.UserRepository;
import com.server.services.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService { 
	  private final PasswordEncoder passwordEncoder;
	  private final JwtService jwtService; 
	  private final UserRepository repository;
	  private final AuthenticationManager authenticationManager;


	  public AuthenticationResponse register(RegisterRequest request) {
		    var user = User.builder()
		        .firstName(request.getFirstname())
		        .lastName(request.getLastname())
		        .email(request.getEmail()) 
		        .address(request.getAddress()) 
		        .date(request.getDate()) 
		        .number(request.getNumber())  
		        .password(passwordEncoder.encode(request.getPassword())) 
		        
		        .role(Role.USER)
		        .build();  
		    var savedUser = repository.save(user); 
		    var jwtToken = jwtService.generateToken(user);

		    return AuthenticationResponse.builder()
		            .token(jwtToken)
		            .build();  
		 
		  } 
	  public AuthenticationResponse authenticate(AuthenticationRequest request) {
		    authenticationManager.authenticate(
		        new UsernamePasswordAuthenticationToken(
		            request.getEmail(),
		            request.getPassword()
		        )
		    );  
		    var user = repository.findByEmail(request.getEmail()).orElseThrow();  
		    var jwtToken = jwtService.generateToken(user);

		    return AuthenticationResponse.builder()
		            .token(jwtToken)
		            .build();   
 
		    
		    
		    
		    
		    
		    
		    }
	  
	  











}
