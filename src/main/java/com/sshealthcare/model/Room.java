package com.sshealthcare.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sshealthcare.enums.RoomStatus;
import com.sshealthcare.enums.StatusType;

@Entity
public class Room {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int Id;
    private String type;
    private String Cost;
    @Enumerated(EnumType.STRING)
    private RoomStatus roomstatus;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCost() {
		return Cost;
	}
	public void setCost(String cost) {
		Cost = cost;
	}
	@Override
	public String toString() {
		return "Room [Id=" + Id + ", type=" + type + ", Cost=" + Cost + "]";
	}
	
    
    
    
}
