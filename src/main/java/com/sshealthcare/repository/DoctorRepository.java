package com.sshealthcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sshealthcare.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

}
