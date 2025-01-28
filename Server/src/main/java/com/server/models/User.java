package com.server.models;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder 
@NoArgsConstructor 
@AllArgsConstructor
@Entity
@Table(name="user")
public class User implements UserDetails  { 
	@Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;   
	@NotNull(message="First name is required!!!:)")
	@Size(min=3,max=30,message="first name must be max 30 characters") 
	private String firstName;  
	@NotNull(message="Last name is required!!!:)")
	@Size(min=3,max=30,message="last name must be max 30 characters") 
	private String lastName;  
	@Email(message="email not valide!! ")
	private String email;   
	@Size(min=3,message="password not valide") 
	private String password;  
	@Size(min=3,message="this confirm you password not valide") 
	private String confirm; 
	private String image; 
	@NotEmpty
	@Past(message="date most be in the past !!")
	private Date date;   
	@NotNull(message="Phone number is required!!")
	@Size(min=8,max=8,message="Phone number must be  8 number!! ") 
	private String number;  
	@NotNull(message="First name is required!!!:)")
	@Size(min=3,max=200,message="Address must be at least  ") 
	private String address; 
	@Enumerated(EnumType.STRING)
	 private Role role;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return List.of(new SimpleGrantedAuthority(role.name()) );
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	} 
	@Override
    public boolean isAccountNonExpired() {
        return true;
    }
    public boolean isAccountNonLocked() {
        return true;
    }
    public boolean isCredentialsNonExpired() {
        return true;
    }
    public boolean isEnabled() {
        return true;
    }
	

}
