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

	public Department insert(Department department) {
		
		return departmentRepository.save(department);
	}

	public Department getById(int depid) throws InvalidIdException {
		Optional<Department> optional= departmentRepository.findById(depid);
		if(!optional.isPresent())
			throw new InvalidIdException("department id invalid");
		return optional.get();
	}

	public List<Department> getAll() {
		List<Department>department = departmentRepository.findAll();
		return department;
	}

	public Department getDepById(int did) throws InvalidIdException{
		Optional<Department> optional = departmentRepository.findById(did);
		if(!optional.isPresent())
			throw new InvalidIdException ("invalid patient id");
		return optional.get();
	}

}
