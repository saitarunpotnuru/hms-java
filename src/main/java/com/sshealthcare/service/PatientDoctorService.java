package com.sshealthcare.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sshealthcare.exception.InvalidIdException;
import com.sshealthcare.model.PatientDoctor;
import com.sshealthcare.model.Receptionist;
import com.sshealthcare.repository.PatientDoctorRepository;
@Service
public class PatientDoctorService {
	
	@Autowired
	private PatientDoctorRepository patientDoctorRepository;
	
	//adding appointments
	public PatientDoctor assignPatientDoctor(PatientDoctor patientDoctor) {
		return patientDoctorRepository.save(patientDoctor);
	}
	//getall
	public List<PatientDoctor> getAllpatientDoctors(Pageable pageable) {
		return patientDoctorRepository.findAll(pageable).getContent();
	}
	
	

	public PatientDoctor getBy(int patientId) throws InvalidIdException {
		Optional<PatientDoctor> optional =  patientDoctorRepository.findById(patientId);
		if(!optional.isPresent()){
			throw new InvalidIdException("Patient ID Invalid");
		
	}
		return optional.get();
	
}
	public  PatientDoctor getByAid(int aid) throws InvalidIdException{
		Optional<PatientDoctor> optional =  patientDoctorRepository.findById(aid);
		if(!optional.isPresent()){
			throw new InvalidIdException("Patient ID Invalid");
		
	}
		return optional.get();
	}

	

	

	
}

	
