package com.sshealthcare.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Doctor {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int Id;
    private String name;
    private String gender;
    private String email;
    private String contact;
    private LocalDate date ;
    private LocalTime startTime;
    private LocalTime endTime;
    private String qualification;
    private double fee;
    private float rating;
    
    
    


	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	@OneToOne
    private User user;
    
    @ManyToOne
    private Department department;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Doctor [Id=" + Id + ", name=" + name + ", gender=" + gender + ", email=" + email + ", contact="
				+ contact + ", date=" + date + ", startTime=" + startTime + ", endTime=" + endTime + ", qualification="
				+ qualification + ", fee=" + fee + ", rating=" + rating + ", user=" + user + ", department="
				+ department + "]";
	}
    
    
    
    
}

