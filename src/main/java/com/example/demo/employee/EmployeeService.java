package com.example.demo.employee;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class EmployeeService {
	private final EmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public List<Employee> getEmployees() {
		return employeeRepository.findAll();
	}

	public void addNewEmployee(Employee employee) {
		if(employee.getSalary() < 22000) {
			throw new IllegalStateException("Salary is too low (it can't be less than 22000).");
		}

		String[] words = employee.getName().split("\\s");


		if(words.length > 1) {
			employee.setName(words[0] + " " + words[1].charAt(0) + ".");
		} else {
			employee.setName(words[0]);
		}
		
		employeeRepository.save(employee);

		System.out.println(employee);
		
	}

	public void deleteEmployee(Long employeeId) {
		boolean exists = employeeRepository.existsById(employeeId);
		if (!exists) {
			throw new IllegalStateException("Employee with id " + employeeId + " does not exist.");
		}
		employeeRepository.deleteById(employeeId);
		
	}

	@Transactional
	public void updateEmployee(Long employeeId, String name, Long project_id, String role, Double salary) {
		
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new IllegalStateException("Employee with id " + employeeId + " does not exist."));
		
		if (name != null && name.length() > 0 && !Objects.equals(employee.getName(), name)) {
			String[] words = name.split("\\s");
			
			if(words.length > 1) {
				employee.setName(words[0] + " " + words[1].charAt(0) + ".");
			}
			else {
				employee.setName(words[0]);
			}
		}


		if (project_id != null && !Objects.equals(employee.getProject_id(), project_id)) {
			employee.setProject_id(project_id);
		}
		
		if (role != null && !Objects.equals(employee.getRole(), role)) {
			employee.setRole(role);
		}
		
		if (salary != null && !Objects.equals(employee.getSalary(), salary)) {
			if(salary < 22000) {
				throw new IllegalStateException("Salary is too low (it can't be less than 22000).");
			}
			employee.setSalary(salary);

		}
	}

}
