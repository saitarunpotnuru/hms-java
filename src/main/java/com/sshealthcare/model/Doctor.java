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
<<<<<<< HEAD
    private String contact;
    private LocalDate date ;
    private LocalTime startTime;
    private LocalTime endTime;

=======
    private String Contact;
>>>>>>> 703a288a9de69f4cfc538b4f9e435d1d80eada4f
    
    
    
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

	public String getContact() {
		return contact;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	
	public void setContact(String contact) {
		this.contact = contact;
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
		return Contact;
	}

	public void setContact(String contact) {
		Contact = contact;
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
<<<<<<< HEAD
		return "Doctor [Id=" + Id + ", name=" + name + ", gender=" + gender + ", email=" + email + ", contact="
				+ contact + ", date=" + date + ", startTime=" + startTime + ", endTime=" + endTime + ", user=" + user
				+ ", department=" + department + "]";
	}

	

	
=======
		return "Doctor [Id=" + Id + ", name=" + name + ", gender=" + gender + ", email=" + email + ", Contact="
				+ Contact + ", user=" + user + ", department=" + department + "]";
	}	
>>>>>>> 703a288a9de69f4cfc538b4f9e435d1d80eada4f

}
