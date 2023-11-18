package com.sshealthcare.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sshealthcare.enums.StatusType;
@Entity
@Table(name = "appointment")
public class PatientDoctor {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	
	private int Id;
	
    private String prescriptionDetails;
    
    private Double fee;
    
    private LocalDate date;
    
    private LocalTime time;
    
    @Enumerated(EnumType.STRING)
    private StatusType status;
    
    public StatusType getStatus() {
		return status;
	}
	public void setStatus(StatusType status) {
		this.status = status;
	}
	@ManyToOne
    private Patient patient;
    
    @ManyToOne
    private Doctor doctor;
    
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getPrescriptionDetails() {
		return prescriptionDetails;
	}
	public void setPrescriptionDetails(String prescriptionDetails) {
		this.prescriptionDetails = prescriptionDetails;
	}
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public Double getFee() {
		return fee;
	}
	public void setFee(Double fee) {
		this.fee = fee;
	}
	@Override
	public String toString() {
		return "PatientDoctor [Id=" + Id + ", prescriptionDetails=" + prescriptionDetails + ", fee=" + fee + ", date="
				+ date + ", time=" + time + ", status=" + status + ", patient=" + patient + ", doctor=" + doctor + "]";
	}
	
	
    
    
   
}
