package com.sshealthcare.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
@Entity
public class Billing {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int Id;
	private double billAmount;
    private String paymentStatus;
	
    @OneToOne
    private Admission admission;
    
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

	public double getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(double billAmount) {
		this.billAmount = billAmount;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Admission getAdmission() {
		return admission;
	}

	public void setAdmission(Admission admission) {
		this.admission = admission;
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
		return "Billing [Id=" + Id + ", billAmount=" + billAmount + ", paymentStatus=" + paymentStatus + ", admission="
				+ admission + ", patient=" + patient + ", doctor=" + doctor + "]";
	}
	
}
