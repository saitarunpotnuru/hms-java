package com.sshealthcare.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sshealthcare.exception.InvalidIdException;
import com.sshealthcare.model.Admission;
import com.sshealthcare.model.Doctor;
import com.sshealthcare.repository.AdmissionRepository;

@Service
public class AdmissionService {
	
	@Autowired
	private AdmissionRepository admissionRepository;
	@Autowired
	private RoomService roomService;
	
	//adding
	public Admission save(Admission admission) {
		
		return admissionRepository.save(admission);
	}
	
	//admissions are getting by admissionId
	public Admission getById(int admissionId) throws InvalidIdException {
		Optional<Admission> optional =  admissionRepository.findById(admissionId);
		if(!optional.isPresent()){
			throw new InvalidIdException("Doctor ID Invalid");
		}
		
		return optional.get();
	}
	
	//getting all admissions
	public List<Admission> getAlladmissions(Pageable pageable) {
		return admissionRepository.findAll(pageable).getContent();
	}
	
	//getting by Id
	public Admission getOne(int id) throws InvalidIdException{
		Optional<Admission> optional =  admissionRepository.findById(id);
		if(!optional.isPresent()){
			throw new InvalidIdException("Admission ID Invalid");
		}
		
		return optional.get();
	}

	public Admission insertAdmission(Admission admission) {
		return admissionRepository.save(admission);
	}

	/*public long getGeneralRoomsCount() {
        return roomService.countRoomsByType("general");
    }

    public long getSpecialRoomsCount() {
        return roomService.countRoomsByType("special");
    }*/

	public int getSpecialPatients(int rid) {
		return admissionRepository.getSpecialPatient(rid);
	}

}
