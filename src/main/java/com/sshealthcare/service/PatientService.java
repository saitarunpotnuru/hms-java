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

	// adding patient
	public Patient insert(Patient patient) {
		return patientRepository.save(patient);
	}


	// get all patients

	public List<Patient> getAll() {
		List<Patient> patient = patientRepository.findAll();
		return patient;
	}




	// get patient by id
	public Patient getone(int pid) throws InvalidIdException {
		Optional<Patient> optional = patientRepository.findById(pid);
		if (!optional.isPresent())
			throw new InvalidIdException("invalid patient id");
		return optional.get();
	}
	

	//updating a patient by id
	public Patient insertPatient(Patient patient) {
		return patientRepository.save(patient);
	}


	//delete a patient by id
	public void deletePatient(Patient patient) {
		patientRepository.delete(patient);
		
	}

	public Patient getByPatient(int pid) {
		// TODO Auto-generated method stub
		return null;
	}

	

	// get appointments by patientId
	public Patient getAppointmentBy(int patientId) throws InvalidIdException {
		Optional<Patient> optional = patientRepository.findById(patientId);
		if (!optional.isPresent()) {
			throw new InvalidIdException("Patient ID Invalid");
		}
		return optional.get();
	}
	//delete patient
	public void delete(int pid) {
		patientRepository.deleteById(pid);
	}


	public Patient getuser(int id) {
		
		return patientRepository.getbyused(id);
	}

	public Patient getOne(int patientId) {
		// TODO Auto-generated method stub
		return null;
	}

}








