package com.sshealthcare.controller;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sshealthcare.dto.PatientDto;
import com.sshealthcare.enums.RoleType;
import com.sshealthcare.exception.InvalidIdException;
import com.sshealthcare.model.Doctor;
import com.sshealthcare.model.Patient;
import com.sshealthcare.model.Receptionist;
import com.sshealthcare.model.User;
import com.sshealthcare.service.ExecutiveService;
import com.sshealthcare.service.PatientService;
import com.sshealthcare.service.UserService;

@RestController
@RequestMapping("/patient")
@CrossOrigin(origins = { "http://localhost:3000" })
public class PatientController {

	@Autowired
	private PatientService patientService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ExecutiveService executiveService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private Logger logger;

	
	
	
	// adding patient
	@PostMapping("/add")
	public Patient insertPatient(@RequestBody Patient patient) {
		// save user info in db
		User user = patient.getUser();
		// encrypting the password
		String passwordPlain = user.getPassword();
		String encodedPassword = passwordEncoder.encode(passwordPlain);
		user.setPassword(encodedPassword);
		user.setRole(RoleType.PATIENT);
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
			public ResponseEntity<?> getone(@PathVariable("pid")int pid) {
			try {
			Patient patient = patientService.getone(pid);
			return ResponseEntity.ok().body(patient);
			}catch(InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
				}
			}
		
 //update patient
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updatePatient(@PathVariable("id")int id,
			@RequestBody PatientDto patientDto){
	try {
		Patient patient = patientService.getone(id);
		if(patientDto.getName()!=null)
			patient.setName(patientDto.getName());
		if(patientDto.getAge()!=0)
			patient.setAge(patientDto.getAge());
		if(patientDto.getGender()!=null)
			patient.setGender(patientDto.getGender());
		if(patientDto.getContact()!=null)
			patient.setContact(patientDto.getContact());
		if(patientDto.getEmail()!=null)
			patient.setEmail(patientDto.getEmail());
		
		patient = patientService.insert(patient);
		logger.info("updated patient name"+patient.getName()+"to"+ patientDto.getName());
		return ResponseEntity.ok().body(patient);
		}catch (InvalidIdException e) {

			return ResponseEntity.badRequest().body(e.getMessage());
		}
}
	
	//delete a patient
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deletePatient(@PathVariable("id") int id) throws InvalidIdException {
		
		//validate id
		Patient patient = patientService.getOne(id);
		//delete
		patientService.deletePatient(patient);
		return ResponseEntity.ok().body("Patient deleted successfully");
	}

}	




		
 

