package com.sshealthcare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sshealthcare.exception.InvalidIdException;
import com.sshealthcare.model.Doctor;
import com.sshealthcare.model.Patient;
import com.sshealthcare.model.PatientDoctor;
import com.sshealthcare.service.DoctorService;
import com.sshealthcare.service.PatientDoctorService;
import com.sshealthcare.service.PatientService;

@RestController
@RequestMapping("/appointment")
public class PatientDoctorController {
	@Autowired
	private PatientDoctorService patientDoctorService ;
	
	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private PatientService patientService;
	
	//adding appointments
	@PostMapping("/add/pid/did")
	public ResponseEntity<?> insertAppointment(@PathVariable("pid")int pid,@PathVariable("did")int did,@RequestBody PatientDoctor patientDoctor) {
		try {
			
			Patient patient = patientService.getone(pid);
			patientDoctor.setPatient(patient);
			
			Doctor doctor = doctorService.getById(did);
			patientDoctor.setDoctor(doctor);
			
			patientDoctor = patientDoctorService.assignPatientDoctor(patientDoctor);
			return ResponseEntity.ok().body(patientDoctor);
		}catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
