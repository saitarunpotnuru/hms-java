package com.sshealthcare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sshealthcare.model.Admission;
import com.sshealthcare.repository.AdmissionRepository;

@Service
public class AdmissionService {
	
	@Autowired
	private AdmissionRepository admissionRepository;
	public Admission assignAdmission(Admission admission) {
		
		return admissionRepository.save(admission);
	}

}
