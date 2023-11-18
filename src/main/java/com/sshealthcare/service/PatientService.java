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


	//get al
	public List<Patient> getAll() {
		List<Patient> patient = patientRepository.findAll();
		return patient;
	}


	//getting by id
	public Patient getone(int pid) throws InvalidIdException{
		Optional<Patient> optional = patientRepository.findById(pid);
		if(!optional.isPresent())
			throw new InvalidIdException ("invalid patient id");
		return optional.get();
	}

	//appointments getting by patientId
	public Patient getBy(int patientId) throws InvalidIdException {
		Optional<Patient> optional = patientRepository.findById(patientId);
		if(!optional.isPresent()) {
			throw new InvalidIdException("Patient ID Invalid");
		}
		return optional.get();
	}


	public Patient update(int id) {
		patientRepository.save(id);
		return null;
	}


	

	
	





	

	
	

	
	public Patient getById(int id) {
		return patientRepository.getById(id);
		
		
	}
}






