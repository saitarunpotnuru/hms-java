package com.sshealthcare.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sshealthcare.exception.InvalidIdException;
import com.sshealthcare.model.Doctor;
import com.sshealthcare.model.Patient;
import com.sshealthcare.repository.DoctorRepository;
import com.sshealthcare.repository.PatientRepository;



@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;
    
    
    //adding
	public Patient insert(Patient patient) {
		return patientRepository.save(patient);
	}


	//getting
	public List<Patient> getAll() {
		List<Patient> patient = patientRepository.findAll();
		return patient;
	}


	//getting by id
	public Patient getById(int pid) throws InvalidIdException{
		Optional<Patient> optional = patientRepository.findById(pid);
		if(!optional.isPresent())
			throw new InvalidIdException ("invalid patient id");
		return optional.get();
	}


	


	



	

	


	

	


	







	



	



	



	


	
}






