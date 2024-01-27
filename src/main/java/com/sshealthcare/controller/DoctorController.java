package com.sshealthcare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sshealthcare.dto.DoctorDto;
import com.sshealthcare.dto.PatientDoctorDto;
import com.sshealthcare.enums.RoleType;
import com.sshealthcare.exception.InvalidIdException;
import com.sshealthcare.model.Department;
import com.sshealthcare.model.Doctor;
import com.sshealthcare.model.PatientDoctor;
import com.sshealthcare.model.User;
import com.sshealthcare.service.DepartmentService;
import com.sshealthcare.service.DoctorService;
import com.sshealthcare.service.PatientDoctorService;
import com.sshealthcare.service.PatientService;
import com.sshealthcare.service.UserService;

@RestController
@RequestMapping("/doctor")
@CrossOrigin(origins = {"http://localhost:3000"})
public class DoctorController {

	@Autowired
	private DoctorService doctorService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	

	@Autowired
	private UserService userService;

	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private PatientDoctorService patientDoctorService;
	
	@Autowired
	private PatientService patientService;

	
	
	
	
	
	// adding doctors with departmentId
	@PostMapping("/add")

	public ResponseEntity<?> assignDoctor( @RequestBody DoctorDto doctor1) {

		Department department = departmentService.getbyname(doctor1.getDepartment());

		User user = doctor1.getUser();
		String passwordPlain = user.getPassword();

		String encodedPassword = passwordEncoder.encode(passwordPlain);
		user.setPassword(encodedPassword);
        
		user.setRole(RoleType.DOCTOR);
		user = userService.insert(user);
		Doctor doctor = new Doctor();
		 doctor.setUser(user);
		 doctor.setDepartment(department);
		 doctor.setName(doctor1.getName());
		 doctor.setGender(doctor1.getGender());
		 doctor.setContact(doctor1.getContact());
		 doctor.setDate(doctor1.getDate());
		 doctor.setEmail(doctor1.getEmail());
		 doctor.setStartTime(doctor1.getStartTime());
		 doctor.setEndTime(doctor1.getEndTime());
		 doctor.setFee(doctor1.getFee());
		 doctor.setQualification(doctor1.getQualification());
		 
		 
		 
		 
		 
		doctor = doctorService.insert(doctor);
		return ResponseEntity.ok().body(doctor);
	}

	
	//get doctors with dep name
	@GetMapping("/getwithname")
	public List<Doctor> getWithName(@RequestParam String name) {
	   
		return doctorService.getWithName(name);
	}

	
	
	

	// getting all doctors
	@GetMapping("/all")
	public List<Doctor> getAllDoctors(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "1000000") Integer size) {

		Pageable pageable = PageRequest.of(page, size);
		return doctorService.getAlldoctors(pageable);
	}

	// getting doctor by Id
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
							@RequestBody Doctor DoctorDto) {
		try {
			// validate id
			Doctor doctor = doctorService.getOne(id);
			if (DoctorDto.getName() != null)
				doctor.setName(DoctorDto.getName());
			
			if (DoctorDto.getGender() != null)
				doctor.setGender(DoctorDto.getGender());
			
			if (DoctorDto.getEmail() != null)
				doctor.setEmail(DoctorDto.getEmail()); 
			
			if(DoctorDto.getContact() != null) 
				doctor.setContact(DoctorDto.getContact()); 
			
			if(DoctorDto.getDate() != null) 
				doctor.setDate(DoctorDto.getDate()); 
			
			if(DoctorDto.getStartTime() != null) 
				doctor.setStartTime(DoctorDto.getStartTime());
			
			if(DoctorDto.getEndTime() != null) 
				doctor.setEndTime(DoctorDto.getEndTime());
			
			 
			doctor = doctorService.insertDoctor(doctor); 
			return ResponseEntity.ok().body(doctor);

		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
	
	//updating appointment status

	@PutMapping("/updateAppointment/{Aid}") 
	public ResponseEntity<?> updateAppointment(@PathVariable("Aid") int Aid,
							@RequestBody PatientDoctor patientDoctor) {
		try {
			// validate id
			PatientDoctor PatientDoctorDto = patientDoctorService.getByAid(Aid);
			if (patientDoctor.getPrescriptionDetails() != null)
				PatientDoctorDto.setPrescriptionDetails(patientDoctor.getPrescriptionDetails());
			if (patientDoctor.getFee() != null)
				PatientDoctorDto.setFee(patientDoctor.getFee());
			if (patientDoctor.getDate() != null)
				PatientDoctorDto.setDate(patientDoctor.getDate());
			if (patientDoctor.getTime() != null)
				PatientDoctorDto.setTime(patientDoctor.getTime());
			if (patientDoctor.getStatus() != null)
				PatientDoctorDto.setStatus(patientDoctor.getStatus());
			
			patientDoctor = patientDoctorService.assignPatientDoctor(PatientDoctorDto); 
			return ResponseEntity.ok().body(PatientDoctorDto);

		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	

	// deleting a Doctor
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteDoctor(@PathVariable("id") int id) throws InvalidIdException {

		// validate id
		Doctor doctor = doctorService.getOne(id);
		// delete
		doctorService.deleteDoctor(id);
		return ResponseEntity.ok().body("Doctor deleted successfully");
	}

}