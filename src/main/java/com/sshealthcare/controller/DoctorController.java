package com.sshealthcare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sshealthcare.exception.InvalidIdException;
import com.sshealthcare.model.Department;
import com.sshealthcare.model.Doctor;
import com.sshealthcare.model.Patient;
import com.sshealthcare.model.Receptionist;
import com.sshealthcare.model.User;
import com.sshealthcare.service.DepartmentService;
import com.sshealthcare.service.DoctorService;
import com.sshealthcare.service.ExecutiveService;
import com.sshealthcare.service.UserService;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

	@Autowired
	private DoctorService doctorService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ExecutiveService executiveService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private DepartmentService departmentService	;
	
	//adding doctors with departmentId
	@PostMapping("/add/{depid}")
	
	public ResponseEntity<?> assignDoctor(@PathVariable("depid") int depid,@RequestBody Doctor doctor) {

		try {
			Department department = departmentService.getById(depid);
			
			doctor.setDepartment(department);
		
		// save user info in db
		User user = doctor.getUser();
		// i am encrypting the password
		String passwordPlain = user.getPassword();

		String encodedPassword = passwordEncoder.encode(passwordPlain);
		user.setPassword(encodedPassword);

		user.setRole("DOCTOR");
		user = userService.insert(user);
		// attach the saved user(in step 1)
		doctor.setUser(user);
		
		doctor = doctorService.insert(doctor);
		return ResponseEntity.ok().body(doctor);
	}   catch(InvalidIdException e) {
		return
				ResponseEntity.badRequest().body(e.getMessage());
		
	}
}
	
	//getting all doctors
	@GetMapping("/all")
	public List<Doctor> getAllDoctors(
			@RequestParam(value="page",required = false,defaultValue = "0") Integer page,
			@RequestParam(value="size",required = false,defaultValue = "1000000") Integer size) {
		
		Pageable pageable =  PageRequest.of(page, size);
		return doctorService.getAlldoctors(pageable);
	}
	
	//getting doctor by Id
	@GetMapping("/getone/{id}")
	public ResponseEntity<?> getOne(@PathVariable("id") int id) {

		try {
			Doctor doctor = doctorService.getOne(id);
			return ResponseEntity.ok().body(doctor);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	//updating doctors by id
	@PutMapping("/update/{id}")  
	public ResponseEntity<?> updateDoctor(@PathVariable("id") int id,
							@RequestBody Doctor newDoctor) {
		try {
			//validate id
			Doctor doctor= doctorService.getOne(id);
			if(newDoctor.getName() != null)
				doctor.setName(newDoctor.getName());
			if(newDoctor.getGender() != null) 
				doctor.setGender(newDoctor.getGender()); 
			if(newDoctor.getEmail() != null) 
				doctor.setEmail(newDoctor.getEmail()); 
			 
			doctor = doctorService.insertDoctor(doctor); 
			return ResponseEntity.ok().body(doctor);

		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
}
	
	//deleting a Doctor
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteDoctor(@PathVariable("id") int id) throws InvalidIdException {
		
		//validate id
		Doctor doctor = doctorService.getOne(id);
		//delete
		doctorService.deleteDoctor(doctor);
		return ResponseEntity.ok().body("Doctor deleted successfully");
	}
	
}