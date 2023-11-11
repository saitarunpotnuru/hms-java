package com.sshealthcare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sshealthcare.model.Patient;
import com.sshealthcare.model.User;
import com.sshealthcare.service.ExecutiveService;
import com.sshealthcare.service.PatientService;
import com.sshealthcare.service.UserService;

@RestController
@RequestMapping("/patient")
public class PatientController {

	@Autowired
	private PatientService patientService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ExecutiveService executiveService;

	@Autowired
	private UserService userService;

	@PostMapping("/add")
	public Patient insertPatient(@RequestBody Patient patient) {

		// save user info in db
		User user = patient.getUser();
		// i am encrypting the password
		String passwordPlain = user.getPassword();

		String encodedPassword = passwordEncoder.encode(passwordPlain);
		user.setPassword(encodedPassword);

		user.setRole("PATIENT");
		user = userService.insert(user);
		// attach the saved user(in step 1)
		patient.setUser(user);

		return patientService.insert(patient);

	}
	
	@GetMapping("/get")
	public List<Patient> getAllPatients(){
		return patientService.getAll();
	}

}
