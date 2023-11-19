package com.sshealthcare.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sshealthcare.dto.DoctorDto;
import com.sshealthcare.dto.PatientDto;
import com.sshealthcare.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
	@Query("select d from Doctor d where id = ?1")
	Optional<Doctor> findById1(int did);
	
	


	

	
}
