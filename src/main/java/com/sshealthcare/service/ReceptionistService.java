package com.sshealthcare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sshealthcare.model.Receptionist;
import com.sshealthcare.repository.ReceptionistRepository;

@Service
public class ReceptionistService {
	
	@Autowired
	private ReceptionistRepository receptionistRepository;

	public Receptionist insert(Receptionist receptionist) {
		return receptionistRepository.save(receptionist);
		
	}

}
