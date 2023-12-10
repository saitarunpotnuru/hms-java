package com.sshealthcare.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sshealthcare.model.PatientDoctor;

public interface PatientDoctorRepository extends JpaRepository<PatientDoctor, Integer>{
	@Query(value = "select * from appointment where id = ?1",nativeQuery = true)
	Optional<PatientDoctor> findBy(int pdid);

	
	@Query("select pd from PatientDoctor pd where pd.patient.id =?1")
	Optional<PatientDoctor> findBypId(int patientId);

	
	@Query("select pd from PatientDoctor pd where pd.patient.id =?1")
	List<PatientDoctor> getAll(int patientId);

	@Query("select pd from PatientDoctor pd where pd.doctor.id =?1")
	List<PatientDoctor> findAppointmentsByDoctorId(int did);


	


     


	

}
