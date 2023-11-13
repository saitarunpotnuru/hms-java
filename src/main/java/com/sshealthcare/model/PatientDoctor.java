package com.sshealthcare.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "appointment")
public class PatientDoctor {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	
	private int Id;
	
    private String prescriptionDetails;
    
    private LocalDateTime slot;
    
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
	public LocalDateTime getSlot() {
		return slot;
	}
	public void setSlot(LocalDateTime slot) {
		this.slot = slot;
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
	@Override
	public String toString() {
		return "PatientDoctor [Id=" + Id + ", prescriptionDetails=" + prescriptionDetails + ", slot=" + slot
				+ ", patient=" + patient + ", doctor=" + doctor + "]";
	}
    
    
   
}
