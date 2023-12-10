package com.sshealthcare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sshealthcare.enums.RoleType;
import com.sshealthcare.enums.StatusType;
import com.sshealthcare.exception.InvalidIdException;
import com.sshealthcare.model.Receptionist;
import com.sshealthcare.model.Room;
import com.sshealthcare.model.User;
import com.sshealthcare.service.RoomService;

@RestController
@RequestMapping("/room")
@CrossOrigin(origins = {"http://localhost:3000"})

public class RoomController {

	@Autowired
	public RoomService roomService;
	
	
	
	
	//adding rooms
	@PostMapping("/add")
	public Room inserRoom(@RequestBody Room room) {
		return roomService.insert(room);
	}
	
	
	
	
	//getting all rooms
	@GetMapping("/all")
	public List<Room> getAllRooms(
			@RequestParam(value="page",required = false,defaultValue = "0") Integer page,
			@RequestParam(value="size",required = false,defaultValue = "1000000") Integer size) {
		
		Pageable pageable =  PageRequest.of(page, size);
		return roomService.getAllrooms(pageable);
	}
	
	
	
	
	//getting rooms by Id
	@GetMapping("/getone/{id}")
	public ResponseEntity<?> getOne(@PathVariable("id") int id) {

		try {
			Room room = roomService.getOne(id);
			return ResponseEntity.ok().body(room);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
}

