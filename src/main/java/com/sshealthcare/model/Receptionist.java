package com.sshealthcare.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Receptionist {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int Id;
    private String name;
    private String gender;
    private String contact;
    private String email;
    private String gender;
    
    
    public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	@OneToOne
    private User user;


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


	public String getContact() {
		return contact;
	}


	public void setContact(String contact) {
		this.contact = contact;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}



	@Override
	public String toString() {
<<<<<<< HEAD
		return "Receptionist [Id=" + Id + ", name=" + name + ", contact=" + contact + ", email=" + email + ", gender="
				+ gender + ", user=" + user + "]";
=======
		return "Receptionist [Id=" + Id + ", name=" + name + ", gender=" + gender + ", contact=" + contact + ", email="
				+ email + ", user=" + user + "]";
>>>>>>> 703a288a9de69f4cfc538b4f9e435d1d80eada4f
	}
	

}
