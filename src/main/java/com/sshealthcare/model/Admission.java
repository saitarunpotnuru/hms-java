package com.sshealthcare.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
@Entity
public class Admission {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int Id;
	private LocalDate admittedDate;
    private LocalDate dischargeDate;
	private String status;
	
	@ManyToOne
	private Room room;
	
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

	public LocalDate getDischargeDate() {
		return dischargeDate;
	}

	public void setDischargeDate(LocalDate dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
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

	public LocalDate getAdmittedDate() {
		return admittedDate;
	}

	public void setAdmittedDate(LocalDate admittedDate) {
		this.admittedDate = admittedDate;
	}

	@Override
	public String toString() {
		return "Admission [Id=" + Id + ", admittedDate=" + admittedDate + ", dischargeDate=" + dischargeDate
				+ ", status=" + status + ", room=" + room + ", patient=" + patient + ", doctor=" + doctor + "]";
	}
	
	
}
