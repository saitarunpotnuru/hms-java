package com.sshealthcare.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sshealthcare.exception.InvalidIdException;
import com.sshealthcare.model.Doctor;
import com.sshealthcare.model.Executive;
import com.sshealthcare.model.Patient;
import com.sshealthcare.model.Receptionist;
import com.sshealthcare.model.User;
import com.sshealthcare.service.DoctorService;
import com.sshealthcare.service.ExecutiveService;
import com.sshealthcare.service.PatientService;
import com.sshealthcare.service.ReceptionistService;
import com.sshealthcare.service.UserService;



@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
public class AuthController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private ExecutiveService executiveService;
	
	@Autowired
	private ReceptionistService receptionistService;
	
	
	@PostMapping("/user/login")
	public User login(Principal principal) {
		String username= principal.getName();
		User user=(User) userService.loadUserByUsername(username);
		return user;
	}


	@PostMapping("/auth/login")
	public ResponseEntity<?> userLogin(Principal principal) {
	    String username = principal.getName();
	    User user = (User) userService.loadUserByUsername(username);

	    if (user != null) {
	        try {
	            switch (user.getRole()) {
	                case PATIENT:
	                	System.out.println(user.getId());
	                    Patient patient = patientService.getuser(user.getId());
	                    return ResponseEntity.ok().body(patient);
	                
	                case DOCTOR:
	                    Doctor doctor = doctorService.getuser(user.getId());
	                    return ResponseEntity.ok(doctor);
	                
	                case EXECUTIVE:
	                    Executive executive = executiveService.getuser(user.getId());
	                    return ResponseEntity.ok(executive);
	                    
	                
	                case RECEPTIONIST:
	                    Receptionist receptionist = receptionistService.getuser(user.getId());
	                    return ResponseEntity.ok(receptionist);
	                
	                default:
	                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unknown user role");
	            }
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
	        }
	    }

	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
	}

}




