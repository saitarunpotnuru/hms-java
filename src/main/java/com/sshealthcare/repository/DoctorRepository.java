package com.sshealthcare.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sshealthcare.dto.DoctorDto;
import com.sshealthcare.dto.PatientDto;
import com.sshealthcare.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
	@Query("select d from Doctor d where id = ?1")
	Optional<Doctor> findById1(int did);

	@Query("select d from Doctor d where d.department.name=?1")
	List<Doctor> findwithname(String name);

	@Query("select d from Doctor d where d.user.id=?1")
	Doctor getByUsed(int id);
	
	


	

	
}
