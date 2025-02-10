package com.server.carelink.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.server.carelink.models.User;
import com.server.carelink.repositories.UserRepository;
import com.server.carelink.services.JwtService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    /**
     * This service class handles the business logic for user authentication and registration.
     */
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    /**
     * Registers a new user. If the email already exists, returns a CONFLICT status.
     * Otherwise, generates an auto-generated password, saves the new user and email him containing his details, and
     * returns an authentication response with a JWT token and user role.
     *
     * @param request containing user details
     * @return ResponseEntity containing the authentication response and HTTP status
     */
    public AuthenticationResponse register(RegisterRequest request) {
       



System.out.println("*************************");
System.out.println(request.getEmail());
            var user = User.builder()
                    .firstName(request.getFirstName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .lastName(request.getLastName())
                    .date(request.getDate())
                    .number(request.getNumber()) 
                    .address(request.getAddress())  
                    .confirm(request.getConfirm())
                    //  Pass the PASSWORD instead of autogenerate
// .password(passwordEncoder.encode(request.getPassword()))
                    .role(request.getRole())
                    .build();
            userRepository.save(user);
            var jwtToken = jwtService.generateToken(user, user.getId());
            	return AuthenticationResponse.builder()
            			.role(user.getRole())
            			.token(jwtToken)
                        .id(user.getId())
            			.build();
        }

    /**
     * Authenticates a user with the provided email and password. If authentication is
     * successful, retrieves the user details, generates a JWT token, and returns
     * an authentication response with the user's role and the token.
     *
     * @param request containing email and password
     * @return AuthenticationResponse containing the user's role and JWT token
     */
    public  AuthenticationResponse authenticate(AuthenticationRequest request) {
        // TODO Auto-generated method stub
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword())
                );
        var user=userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken= jwtService.generateToken(user, user.getId());
        return AuthenticationResponse.builder()
        		.role(user.getRole())
                .token(jwtToken)
                .id(user.getId())
                .build();
    }



}
