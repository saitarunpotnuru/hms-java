package com.sshealthcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sshealthcare.model.Admission;

public interface AdmissionRepository extends JpaRepository<Admission, Integer>{

	@Query("SELECT COUNT(a) FROM Admission a WHERE a.room.id = ?1")
	int getSpecialPatient(int rid);

}
