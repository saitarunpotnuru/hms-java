package com.sshealthcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sshealthcare.model.Receptionist;

public interface ReceptionistRepository extends JpaRepository<Receptionist, Integer> {
	
	
	@Query("select r from Receptionist r where r.user.id=?1")
	Receptionist getByUsed(int id);

}

