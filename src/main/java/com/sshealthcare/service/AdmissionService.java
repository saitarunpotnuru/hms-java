package com.sshealthcare.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sshealthcare.exception.InvalidIdException;
import com.sshealthcare.model.Admission;
import com.sshealthcare.repository.AdmissionRepository;

@Service
public class AdmissionService {
	
	@Autowired
	private AdmissionRepository admissionRepository;
	public Admission assignAdmission(Admission admission) {
		
		return admissionRepository.save(admission);
	}
	
	public Admission getById(int admissionId) throws InvalidIdException {
		Optional<Admission> optional =  admissionRepository.findById(admissionId);
		if(!optional.isPresent()){
			throw new InvalidIdException("Doctor ID Invalid");
		}
		
		return optional.get();
	}

}
