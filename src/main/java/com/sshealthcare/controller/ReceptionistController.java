package com.sshealthcare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}