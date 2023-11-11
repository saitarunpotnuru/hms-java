package com.sshealthcare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sshealthcare.model.Doctor;
import com.sshealthcare.repository.DoctorRepository;


@Service
public class DoctorService {
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	@PostMapping
	
	public Doctor insert(@RequestBody Doctor doctor) {
		
		return doctorRepository.save(doctor);
	}

}
