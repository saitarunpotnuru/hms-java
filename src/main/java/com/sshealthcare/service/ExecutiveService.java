package com.sshealthcare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sshealthcare.model.Executive;
import com.sshealthcare.repository.ExecutiveRepository;


@Service

public class ExecutiveService {
	
	@Autowired
	private ExecutiveRepository executiveRepository;
	
	public Executive insert(Executive executive) {
		// TODO Auto-generated method stub
		return executiveRepository.save(executive);
	}

	public Executive getById(int eid) {
		// TODO Auto-generated method stub
		return null;
	}

}
