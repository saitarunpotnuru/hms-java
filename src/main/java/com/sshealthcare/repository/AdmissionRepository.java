package com.sshealthcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sshealthcare.model.Admission;

public interface AdmissionRepository extends JpaRepository<Admission, Integer>{

}
