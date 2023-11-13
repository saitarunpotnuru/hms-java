package com.sshealthcare.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sshealthcare.model.PatientDoctor;
import com.sshealthcare.repository.PatientDoctorRepository;
@Service
public class PatientDoctorService {
	
	@Autowired
	private PatientDoctorRepository patientDoctorRepository;
	
	//adding
	public PatientDoctor assignPatientDoctor(PatientDoctor patientDoctor) {
		return patientDoctorRepository.save(patientDoctor);
	}

}
