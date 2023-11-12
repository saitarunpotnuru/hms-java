package com.sshealthcare.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sshealthcare.exception.InvalidIdException;
import com.sshealthcare.model.Department;
import com.sshealthcare.model.Patient;
import com.sshealthcare.repository.DepartmentRepository;


@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository departmentRepository;

	//ADDING
	public Department insert(Department department) {
		
		return departmentRepository.save(department);
	}
	
	//GET ALL DEPARTMENTS
	public List<Department> getAll() {
		List<Department>department = departmentRepository.findAll();
		return department;
	}
	
	//GET BY ID
	public Department getById(int depid) throws InvalidIdException {
		Optional<Department> optional= departmentRepository.findById(depid);
		if(!optional.isPresent())
			throw new InvalidIdException("department id invalid");
		return optional.get();
	}

	
	

	

}
