package com.sshealthcare.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sshealthcare.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer>{

	Patient save(int id);
	@Query(value = "select * from patient where id = ?1",nativeQuery = true)
	Optional<Patient> findById1(int id);

}
