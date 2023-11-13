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
	
	//adding
	public PatientDoctor assignPatientDoctor(PatientDoctor patientDoctor) {
		return patientDoctorRepository.save(patientDoctor);
	}

	public List<PatientDoctor> getAllpatientDoctors(Pageable pageable) {
		return patientDoctorRepository.findAll(pageable).getContent();
	}

	public PatientDoctor getOne(int id) throws InvalidIdException {
		Optional<PatientDoctor> optional =  patientDoctorRepository.findById(id);
		if(!optional.isPresent()){
			throw new InvalidIdException("Receptionist ID Invalid");
		
	}
		return optional.get();
	
}

}
