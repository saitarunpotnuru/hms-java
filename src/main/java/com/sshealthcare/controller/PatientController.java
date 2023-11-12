package com.sshealthcare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sshealthcare.exception.InvalidIdException;
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

	// adding patient
	@PostMapping("/add")
	public Patient insertPatient(@RequestBody Patient patient) {

		// save user info in db
		User user = patient.getUser();
		// encrypting the password
		String passwordPlain = user.getPassword();

		String encodedPassword = passwordEncoder.encode(passwordPlain);
		user.setPassword(encodedPassword);

		user.setRole("PATIENT");
		user = userService.insert(user);
		// attach the saved user(in step 1)
		patient.setUser(user);

		return patientService.insert(patient);

	}

	// get all patients
	@GetMapping("/get")
	public List<Patient> getAllPatients() {
		return patientService.getAll();
	}
	//get patient by id
	@GetMapping("/get/{pid}")
	public ResponseEntity<?> getById(@PathVariable("pid")int pid) {
		try {
			Patient patient = patientService.getById(pid);
			return ResponseEntity.ok().body(patient);
		}catch(InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
	
	

}
