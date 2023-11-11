package com.sshealthcare.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sshealthcare.model.Patient;
import com.sshealthcare.repository.PatientRepository;



@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;
    
    

	public Patient insert(Patient patient) {
		return patientRepository.save(patient);
	}


	
}






