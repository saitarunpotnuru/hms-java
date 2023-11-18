package com.sshealthcare.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sshealthcare.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

	

	
}
