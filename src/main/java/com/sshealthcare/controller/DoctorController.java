package com.sshealthcare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sshealthcare.exception.InvalidIdException;
import com.sshealthcare.model.Department;
import com.sshealthcare.model.Doctor;
import com.sshealthcare.model.User;
import com.sshealthcare.service.DepartmentService;
import com.sshealthcare.service.DoctorService;
import com.sshealthcare.service.UserService;



@RestController
@RequestMapping("/doctor")
public class DoctorController {
	
	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private UserService userService;	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private DepartmentService departmentService;
	
	@PostMapping("/add/{depid}")
	public ResponseEntity<?> insertDoctor(@PathVariable("depid")int depid,@RequestBody Doctor doctor) {
		//save user info in db
		
				try {
					Department department = departmentService.getById(depid);
					doctor.setDepartment(department);
					User user=doctor.getUser();
					
					// i am encrypting the password
					String passwordPlain = user.getPassword();
					
					String encodedPassword = passwordEncoder.encode(passwordPlain);
					user.setPassword(encodedPassword);
					
					user.setRole("DOCTOR");
					user = userService.insert(user);
					// attach the saved user(in step 1)
					doctor.setUser(user);
					return ResponseEntity.ok().body(doctor);
				} catch (InvalidIdException e) {
					return ResponseEntity.badRequest().body(e.getMessage());
					
					
				}
				
		
	}

}
