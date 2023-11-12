package com.sshealthcare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sshealthcare.exception.InvalidIdException;
import com.sshealthcare.model.Receptionist;
import com.sshealthcare.model.User;
import com.sshealthcare.service.ReceptionistService;
import com.sshealthcare.service.UserService;

@RestController
@RequestMapping("/receptionist")
public class ReceptionistController {
	
	@Autowired
	private ReceptionistService receptionistService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	//adding all receptionists
	@PostMapping("/add")
	
		public Receptionist insertReceptionist (@RequestBody Receptionist receptionist) {
			
			// save user info in db
			User user = receptionist.getUser();
			
			// i am encrypting the password
			String passwordPlain = user.getPassword();
			
			String encodedPassword = passwordEncoder.encode( passwordPlain);
			user.setPassword(encodedPassword);
			
			user.setRole("Receptionist");
			user = userService.insert(user);
			
			// attach the saved user(in step 1)
			receptionist.setUser(user);
			
			return receptionistService.insert(receptionist);
			
	}
	
	//getting all receptionists
	@GetMapping("/all")
	public List<Receptionist> getAllReceptionists(
			@RequestParam(value="page",required = false,defaultValue = "0") Integer page,
			@RequestParam(value="size",required = false,defaultValue = "1000000") Integer size) {
		
		Pageable pageable =  PageRequest.of(page, size);
		return receptionistService.getAllreceptionists(pageable);
	}
	
	//getting receptionists by Id
	@GetMapping("/getone/{id}")
	public ResponseEntity<?> getOne(@PathVariable("id") int id) {

		try {
			Receptionist receptionist = receptionistService.getOne(id);
			return ResponseEntity.ok().body(receptionist);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	//update receptionists by Id
	@PutMapping("/update/{id}")  //:update: which record to update?   give me new value for update
	public ResponseEntity<?> updateReceptionist(@PathVariable("id") int id,
							@RequestBody Receptionist newReceptionist) {
		try {
			//validate id
			Receptionist Receptionist = receptionistService.getOne(id);
			if(newReceptionist.getName() != null)
				Receptionist.setName(newReceptionist.getName());
			if(newReceptionist.getContact() != null) 
				Receptionist.setContact(newReceptionist.getContact()); 
			if(newReceptionist.getEmail() != null) 
				Receptionist.setEmail(newReceptionist.getEmail()); 
			 
			Receptionist = receptionistService.insertReceptionist(Receptionist); 
			return ResponseEntity.ok().body(Receptionist);

		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
}
}