package com.sshealthcare.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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

	public List<Receptionist> getAllreceptionists(Pageable pageable) {
		return receptionistRepository.findAll(pageable).getContent();
	}

}
