package com.sshealthcare.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
	
	public List<Doctor> getAlldoctors(Pageable pageable) {
		return doctorRepository.findAll(pageable).getContent();
	}


}
