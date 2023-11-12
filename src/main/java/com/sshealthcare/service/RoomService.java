package com.sshealthcare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sshealthcare.model.Room;
import com.sshealthcare.repository.RoomRepository;

@Service
public class RoomService {
	
	@Autowired
	private RoomRepository roomRepository;
	
	public Room insert(Room room) {
		return roomRepository.save(room);
	}

}
