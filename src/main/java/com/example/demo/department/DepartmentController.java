package com.example.demo.department;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/department")
public class DepartmentController {
	private final DepartmentService departmentService;

	@Autowired
	public DepartmentController(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	@GetMapping()
	public List<Department> getDepartments() {
		return departmentService.getDepartments();
	}

	@PostMapping
	public void registerNewDepartment(@RequestBody Department department) {
		departmentService.addNewDepartment(department);
	}

	@DeleteMapping(path = "{departmentId}")
	public void deleteStudent(@PathVariable("departmentId") Long id) {
		departmentService.deleteDepartment(id);
	}

	@PutMapping(path = "{departmentId}")
	public void updateDepartment(
			@PathVariable("departmentId") Long departmentId, 
			@RequestParam(required = false) String department_name,
			@RequestParam(required = false) Long manager_id
			) 
	{
		departmentService.updateDepartment(departmentId, department_name, manager_id);
	}
}
