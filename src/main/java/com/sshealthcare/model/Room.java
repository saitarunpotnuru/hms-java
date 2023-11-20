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
    private int room_no;
    private Double Cost;
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
	public double getCost() {
		return Cost;
	}
	public void setCost(double cost) {
		Cost = cost;
	}
	public int getRoom_no() {
		return room_no;
	}
	public void setRoom_no(int room_no) {
		this.room_no = room_no;
	}
	public RoomStatus getRoomstatus() {
		return roomstatus;
	}
	public void setRoomstatus(RoomStatus roomstatus) {
		this.roomstatus = roomstatus;
	}
	public void setCost(Double cost) {
		Cost = cost;
	}
	@Override
	public String toString() {
		return "Room [Id=" + Id + ", type=" + type + ", room_no=" + room_no + ", Cost=" + Cost + ", roomstatus="
				+ roomstatus + "]";
	}
	
    
    
    
}
