package com.sshealthcare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sshealthcare.enums.RoleType;
import com.sshealthcare.enums.RoleType;
import com.sshealthcare.exception.InvalidIdException;
import com.sshealthcare.model.Department;
import com.sshealthcare.model.Executive;
import com.sshealthcare.model.User;
import com.sshealthcare.service.ExecutiveService;
import com.sshealthcare.service.PatientService;
import com.sshealthcare.service.UserService;

@RestController
@RequestMapping("/executive")
@CrossOrigin(origins = {"http://localhost:3000"})

public class ExecutiveController {
	@Autowired
	private ExecutiveService executiveService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserService userService;

	
	
	
	// adding executive
	@PostMapping("/add")

	public Executive inseExecutive(@RequestBody Executive executive) {
		User user = executive.getUser();
		String passwordPlain = user.getPassword();
		String encodedPassword = passwordEncoder.encode(passwordPlain);
		user.setPassword(encodedPassword);
		user.setRole(RoleType.EXECUTIVE);
		user = userService.insert(user);
		executive.setUser(user);
		return executiveService.insert(executive);

	}

	
	// get executive
	@GetMapping("/get")
	public List<Executive> getAllexecutive() {
		return executiveService.getAll();
	}
	
	
	// get executive by id
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getOne(@PathVariable("id") int id) {
		try {
			Executive executive = executiveService.getOne(id);
			return ResponseEntity.ok().body(executive);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
	// update executive
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateExecutive(@PathVariable("id") int id, @RequestBody Executive newExecutive) {
		try {
			Executive executive = executiveService.getOne(id);
			if(newExecutive.getName()!=null)
				executive.setName(newExecutive.getName());
			if(newExecutive.getGender()!=null)
				executive.setGender(newExecutive.getGender());
			if(newExecutive.getContact()!= null)
				executive.setContact(newExecutive.getContact());
			if(newExecutive.getEmail()!=null)
				executive.setEmail(newExecutive.getEmail());
			
			executive = executiveService.insert(executive);
			return ResponseEntity.ok().body(executive);
		}catch(InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

}
