package com.sshealthcare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sshealthcare.model.Room;
import com.sshealthcare.service.RoomService;

@RestController
@RequestMapping("/room")
public class RoomController {

	@Autowired
	public RoomService roomService;
	
	@PostMapping("/add")
	public Room inserRoom(@RequestBody Room room) {
		return roomService.insert(room);
	}
}

