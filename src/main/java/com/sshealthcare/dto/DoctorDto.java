package com.sshealthcare.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.sshealthcare.model.User;

public class DoctorDto {
	private int Id;
    private String name;
    private String gender;
    private String email;
    
    private String contact;
    private LocalDate date ;
    private LocalTime startTime;
    private LocalTime endTime;
    private  String qualification;
    private long fee;
    private String department;
    
    private User user;
    
    
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
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
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public long getFee() {
		return fee;
	}
	public void setFee(long fee) {
		this.fee = fee;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	@Override
	public String toString() {
		return "DoctorDto [Id=" + Id + ", name=" + name + ", gender=" + gender + ", email=" + email + ", contact="
				+ contact + ", date=" + date + ", startTime=" + startTime + ", endTime=" + endTime + ", qualification="
				+ qualification + ", fee=" + fee + ", department=" + department + ", user=" + user + "]";
	}
    
	

}
