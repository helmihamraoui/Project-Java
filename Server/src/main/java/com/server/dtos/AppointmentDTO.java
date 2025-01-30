package com.server.dtos;

import java.util.Date;

public class AppointmentDTO {

	private Long id;
	private Date date;
	private DoctorDTO doctor;
	private PatientDTO patient;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public DoctorDTO getDoctor() {
		return doctor;
	}
	public void setDoctor(DoctorDTO doctor) {
		this.doctor = doctor;
	}
	public PatientDTO getPatient() {
		return patient;
	}
	public void setPatient(PatientDTO patient) {
		this.patient = patient;
	}
	
	
}
