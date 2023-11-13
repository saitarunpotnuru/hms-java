package com.sshealthcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sshealthcare.model.PatientDoctor;

public interface PatientDoctorRepository extends JpaRepository<PatientDoctor, Integer>{

}
