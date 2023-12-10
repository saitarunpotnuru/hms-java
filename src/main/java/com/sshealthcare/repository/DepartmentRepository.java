package com.sshealthcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sshealthcare.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer>{
    
	
	@Query("select d from Department d where d.name=?1")
	Department getbyname(String department);

}
