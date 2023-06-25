package com.example.demo.employee;

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
@RequestMapping(path = "api/v1/employee")
public class EmployeeController {
	private final EmployeeService employeeService;

	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping()
	public List<Employee> getEmployees() {
		return employeeService.getEmployees();
	}

	@PostMapping
	public void registerNewEmployee(@RequestBody Employee employee) {
		employeeService.addNewEmployee(employee);
	}

	@DeleteMapping(path = "{employeeId}")
	public void deleteEmployee(@PathVariable("employeeId") Long employeeId) {
		employeeService.deleteEmployee(employeeId);
	}

	@PutMapping(path = "{employeeId}")
	public void updateEmployee(
			@PathVariable("employeeId") Long employeeId, 
			@RequestParam(required = false) String name,
			@RequestParam(required = false) Long project_id,
			@RequestParam(required = false) String role,
			@RequestParam(required = false) Double salary
			) 
	{
		employeeService.updateEmployee(employeeId, name, project_id, role, salary);
	}
}
