package com.sshealthcare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sshealthcare.model.Department;
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

}
