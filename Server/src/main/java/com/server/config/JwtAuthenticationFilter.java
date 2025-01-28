package com.server.config;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.server.services.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
@Component 
@RequiredArgsConstructor

public class JwtAuthenticationFilter extends OncePerRequestFilter {
private final JwtService jwtService = new JwtService();
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub 
		final String  authHeader=request.getHeader("Authorizatin");  
		final String jwt;  
		if (authHeader==null||!authHeader.startsWith("Bearer")) { 
			filterChain.doFilter(request, response);
			return ; 
			
		}jwt=authHeader.substring(7); 
		userEmail=jwtService. extractUsername(jwt); 
		
		
		
		
		
	}

}
