package com.sshealthcare.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sshealthcare.exception.InvalidIdException;
import com.sshealthcare.model.Receptionist;
import com.sshealthcare.repository.ReceptionistRepository;

@Service
public class ReceptionistService {
	
	@Autowired
	private ReceptionistRepository receptionistRepository;

	//adding receptionists
	public Receptionist insert(Receptionist receptionist) {
		return receptionistRepository.save(receptionist);
		
	}
	
	//get all receptionists
	public List<Receptionist> getAllreceptionists(Pageable pageable) {
		return receptionistRepository.findAll(pageable).getContent();
	}
	
	//get receptionists by Id
	public Receptionist getOne(int id) throws InvalidIdException {
		Optional<Receptionist> optional =  receptionistRepository.findById(id);
		if(!optional.isPresent()){
			throw new InvalidIdException("Receptionist ID Invalid");
		
	}
		return optional.get();
	
}
	
	//update receptionists
	public Receptionist insertReceptionist(Receptionist receptionist) {
		return receptionistRepository.save(receptionist);
	}

	//delete Receptionist
	public void deleteReceptionist(Receptionist receptionist) {
		receptionistRepository.delete(receptionist);
		
	}

	public Receptionist getuser(int id) {
		
		return receptionistRepository.getByUsed(id);
	}


}
