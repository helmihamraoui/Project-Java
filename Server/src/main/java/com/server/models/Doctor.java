package com.server.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

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
	
	public Doctor() {}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSpecialties() {
		return specialties;
	}
	public void setSpecialties(String specialties) {
		this.specialties = specialties;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public Integer getLicenseNumb() {
		return licenseNumb;
	}
	public void setLicenseNumb(Integer licenseNumb) {
		this.licenseNumb = licenseNumb;
	}
	public Integer getExperience() {
		return experience;
	}
	public void setExperience(Integer experience) {
		this.experience = experience;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
	
}
