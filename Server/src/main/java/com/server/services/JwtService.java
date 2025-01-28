package com.server.services;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class JwtService {  
	private static final String SECRET_KEY="6b3c4d4a36c2a0b4cc1b63e761ae1835c00e361207d4d79fc7538609a711abe6"; 
	
	String extractUsername(String token) {  return null; 

		
	} 
	private Claims extractAllClaims(String token) { 
			return Jwts
					.parserBuilder()
					.setSigningKey(getSignInKey()) 
					.build()
					.parseClaimsJws(token)
					.getBody();  
					
; 
		
	}
	
}
