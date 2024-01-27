package com.sshealthcare.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sshealthcare.dto.DoctorDto;
import com.sshealthcare.dto.PatientDto;
import com.sshealthcare.exception.InvalidIdException;
import com.sshealthcare.model.Doctor;
import com.sshealthcare.model.Receptionist;
import com.sshealthcare.repository.DoctorRepository;

@Service
public class DoctorService {

	@Autowired
	private DoctorRepository doctorRepository;

	// adding
	public Doctor insert(Doctor doctor) {
		return doctorRepository.save(doctor);
	}

	// getting all doctors
	public List<Doctor> getAlldoctors(Pageable pageable) {
		return doctorRepository.findAll(pageable).getContent();
	}

	// getting doctors by Id
	public Doctor getOne(int id) throws InvalidIdException {
		Optional<Doctor> optional = doctorRepository.findById(id);
		if (!optional.isPresent()) {
			throw new InvalidIdException("Doctor ID Invalid");
		}

		return optional.get();
	}

	// admissions and billings are getting by doctorId
	public Doctor getById(int did) throws InvalidIdException {
		Optional<Doctor> optional = doctorRepository.findById(did);
		if (!optional.isPresent()) {
			throw new InvalidIdException("Doctor ID Invalid");
		}

		return optional.get();
	}

	// update Doctors
	public Doctor insertDoctor(Doctor doctor) {
		return doctorRepository.save(doctor);
	}

	// delete doctor
	public void deleteDoctor(Doctor doctor) {
		doctorRepository.delete(doctor);

	}

	// get appointments by doctorId
	public Doctor getBydid(int doctorId) throws InvalidIdException {
		Optional<Doctor> optional = doctorRepository.findById(doctorId);
		if (!optional.isPresent()) {
			throw new InvalidIdException("Doctor ID Invalid");
		}
		return optional.get();
	}

	// get appointment by doctorId
	public Doctor getByDoctor(int did) throws InvalidIdException {
		Optional<Doctor> optional = doctorRepository.findById1(did);
		if (!optional.isPresent()) {
			throw new InvalidIdException("Doctor ID Invalid");
		}
		return optional.get();
	}

	public List<Doctor> getWithName(String name) {
		// TODO Auto-generated method stub
		return doctorRepository.findwithname(name);
	}

	public Doctor getuser(int id) {

		return doctorRepository.getByUsed(id);
	}

	public void deleteDoctor(int id) {
		doctorRepository.deleteById(id);

	}

}
