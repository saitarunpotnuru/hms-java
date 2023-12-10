package com.sshealthcare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sshealthcare.exception.InvalidIdException;
import com.sshealthcare.model.Department;
import com.sshealthcare.model.Doctor;
import com.sshealthcare.model.Patient;
import com.sshealthcare.service.DepartmentService;
import com.sshealthcare.service.DoctorService;

@RestController
@RequestMapping("/department")
@CrossOrigin(origins = {"http://localhost:3000"})
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;

	
	// adding department
	@PostMapping("/add")
	public Department insertDepartment(@RequestBody Department department) {
		return departmentService.insert(department);
	}

	
	// getting department
	@GetMapping("/get")
	public List<Department> getAlldepartments() {
		return departmentService.getAll();
	}


	// get department by id
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getOne(@PathVariable("id") int id) {
		try {
			Department department = departmentService.getOne(id);
			return ResponseEntity.ok().body(department);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
	
	// update department
	@PutMapping("/update/{id}")
	public ResponseEntity<?> getOne(@PathVariable("id") int id, @RequestBody Department newDepartment) {
		try {
			Department department = departmentService.getOne(id);
			if (newDepartment.getName() != null)
				department.setName(newDepartment.getName());

			department = departmentService.insert(department);
			return ResponseEntity.ok().body(department);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
