package com.sshealthcare.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sshealthcare.dto.PatientDoctorDto;
import com.sshealthcare.exception.InvalidIdException;
import com.sshealthcare.model.Patient;
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
	//get all appointments
	public List<PatientDoctor> getAllpatientDoctors(Pageable pageable) {
		return patientDoctorRepository.findAll(pageable).getContent();
	}

	
	
	//get appointment by appointment id
	public PatientDoctor getBypdId(int pdid) throws InvalidIdException{
		Optional<PatientDoctor> optional = patientDoctorRepository.findBy(pdid);
		if(!optional.isPresent()) {
			throw new InvalidIdException("invalid pdid");
		}
		return optional.get();
	}

	public PatientDoctor postPatientDoctor(PatientDoctor patientDoctor) {
		// TODO Auto-generated method stub
		return patientDoctorRepository.save(patientDoctor);
	}
 
	
	//get list of appointments by pid
			public List<PatientDoctor> getAll(int patientId) {
				return patientDoctorRepository.getAll(patientId);
			}
	
	
	//updating appointment status
		public  PatientDoctor getByAid(int aid) throws InvalidIdException{
			Optional<PatientDoctor> optional =  patientDoctorRepository.findById(aid);
			if(!optional.isPresent()){
				throw new InvalidIdException("Patient ID Invalid");
			
		}
			return optional.get();
		}

		
		
		public List<PatientDoctor> getAppointmentsByDoctorId(int did) {
			// TODO Auto-generated method stub
			return patientDoctorRepository.findAppointmentsByDoctorId(did);
		}}
	
	
	
	

	

	



