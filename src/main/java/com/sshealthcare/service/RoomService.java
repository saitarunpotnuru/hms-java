package com.sshealthcare.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sshealthcare.exception.InvalidIdException;
import com.sshealthcare.model.Receptionist;
import com.sshealthcare.model.Room;
import com.sshealthcare.repository.RoomRepository;

@Service
public class RoomService {
	
	@Autowired
	private RoomRepository roomRepository;
	
	//adding rooms
	public Room insert(Room room) {
		return roomRepository.save(room);
	}
	
	//admissions are getting by room Id
	public Room getById(int rid) throws InvalidIdException {
		Optional<Room> optional =  roomRepository.findById(rid);
		if(!optional.isPresent()){
			throw new InvalidIdException("Room ID Invalid");
		}
		
		return optional.get();
	}
	
	//getting all rooms
	public List<Room> getAllrooms(Pageable pageable) {
		return roomRepository.findAll(pageable).getContent();
	}
	
	//getting rooms by Id
	public Room getOne(int id) throws InvalidIdException {
		Optional<Room> optional =  roomRepository.findById(id);
		if(!optional.isPresent()){
			throw new InvalidIdException("Room ID Invalid");
		
	}
		return optional.get();

}

	public long countRoomsByType(String type) {
        return roomRepository.countRoomsByType(type);
    }

}
