package com.sshealthcare.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class PatientDoctorDto {
	
private int Id;
	
    private String prescriptionDetails;
    
    private Double fee;
    
    private LocalDate date;
    
    private LocalTime time;

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

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
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
    
    

}
