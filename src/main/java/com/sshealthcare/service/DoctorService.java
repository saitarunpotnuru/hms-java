package com.sshealthcare.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sshealthcare.exception.InvalidIdException;
import com.sshealthcare.model.Doctor;
import com.sshealthcare.repository.DoctorRepository;

@Service
public class DoctorService {
	
	@Autowired
	private DoctorRepository doctorRepository;

	
	public Doctor insert(Doctor doctor) {
		return doctorRepository.save(doctor);
	}
	
	public List<Doctor> getAlldoctors(Pageable pageable) {
		return doctorRepository.findAll(pageable).getContent();
	}

	public Doctor getOne(int id) throws InvalidIdException{
		Optional<Doctor> optional =  doctorRepository.findById(id);
		if(!optional.isPresent()){
			throw new InvalidIdException("Receptionist ID Invalid");
		}
		
		return optional.get();
	}
	
	public Doctor getById(int did) throws InvalidIdException {
		Optional<Doctor> optional =  doctorRepository.findById(did);
		if(!optional.isPresent()){
			throw new InvalidIdException("Doctor ID Invalid");
		}
		
		return optional.get();
	}


}
