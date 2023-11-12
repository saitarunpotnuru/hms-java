package com.sshealthcare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sshealthcare.model.Doctor;
import com.sshealthcare.repository.DoctorRepository;

@Service
public class DoctorService {
	
	@Autowired
	private DoctorRepository doctorRepository;

	
	public Doctor insert(Doctor doctor) {
		return doctorRepository.save(doctor);
	}

}
