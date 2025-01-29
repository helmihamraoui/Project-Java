package com.server.dtos;

public class DoctorDTO {
	private Long id;
	private String specialties;
	private String jobTitle;
	private Integer licenseNumb;
	private Integer experience;
	private UserDTO user;
	private AppointmentDTO appointment;
	
	
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	public AppointmentDTO getAppointment() {
		return appointment;
	}
	public void setAppointment(AppointmentDTO appointment) {
		this.appointment = appointment;
	}
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
	
	
}
