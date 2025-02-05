package com.server.carelink.models;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="doctors")
public class Doctor {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotEmpty
	private String specialties;
	
	@NotEmpty
	private String jobTitle;
	
	@NotNull
	private Integer licenseNumb;
	
	@NotNull
	private Integer experience;
	
	@Column(updatable=false)
	private Date createdAt;
	private Date updatedAt;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="user_id",referencedColumnName="id")
	private User user;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="doctor")
	private List<Appointment> appointments;
	
	
	
	
}
