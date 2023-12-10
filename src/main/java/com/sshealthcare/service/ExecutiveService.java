package com.sshealthcare.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sshealthcare.exception.InvalidIdException;
import com.sshealthcare.model.Executive;
import com.sshealthcare.repository.ExecutiveRepository;


@Service

public class ExecutiveService {
	
	@Autowired
	private ExecutiveRepository executiveRepository;
	
	
	//adding
	public Executive insert(Executive executive) {
		// TODO Auto-generated method stub
		return executiveRepository.save(executive);
	}
	
	//get all
		public List<Executive> getAll() {
			
			return executiveRepository.findAll();
		}
	
	//get by id
	public Executive getOne(int eid) throws InvalidIdException{
		Optional<Executive> optional = executiveRepository.findById(eid);
		if(!optional.isPresent())
			throw new InvalidIdException("invalid executive id");
		return optional.get();
		
		
		
	}

	public Executive getuser(int id) {
		
		return executiveRepository.getByUsed(id);
	}
	
	

}
