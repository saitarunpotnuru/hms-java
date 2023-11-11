package com.sshealthcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sshealthcare.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer>{

}
