package com.sshealthcare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sshealthcare.exception.InvalidIdException;
import com.sshealthcare.model.Department;
import com.sshealthcare.model.Patient;
import com.sshealthcare.service.DepartmentService;

@RestController
@RequestMapping("/department")
public class DepartmentController {
	@Autowired
	private DepartmentService departmentService;
	
	
	//adding department
	@PostMapping("/add")
	public Department insertDepartment(@RequestBody Department department) {
		return departmentService.insert(department);
	}
	
	//getting department
	@GetMapping("/get")
	public List<Department> getAlldepartments(){
		return departmentService.getAll();
	}
	
	//get department by id
		@GetMapping("/get/{did}")
		public ResponseEntity<?> getById(@PathVariable("did")int did) throws InvalidIdException {
			Department department = departmentService.getDepById(did);
			return ResponseEntity.ok().body(department);
		}

}
