package com.example.demo.employee;

import com.example.demo.department.Department;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table
public class Employee {
	@Id
	@SequenceGenerator(name = "employee_sequence", sequenceName = "employee_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_sequence")
	private Long employee_id;
	private String name;
	private Long project_id;
	private String role;
	private Double salary;


	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "department_id")
	private Department department;
	
	public Employee() {
	}
	
	public Employee(String name, Long project_id, String role, Double salary, Department department) {
		this.name = name;
		this.project_id = project_id;
		this.role = role;
		this.salary = salary;
		this.department = department;
	}

	public Employee(Long employee_id, String name, Long project_id, String role, Double salary, Department department) {
		this.employee_id = employee_id;
		this.name = name;
		this.project_id = project_id;
		this.role = role;
		this.salary = salary;
		this.department = department;
	}

	public Long getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(Long employee_id) {
		this.employee_id = employee_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getProject_id() {
		return project_id;
	}

	public void setProject_id(Long project_id) {
		this.project_id = project_id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}
	
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Employee [employee_id=" + employee_id + ", name=" + name + ", project_id=" + project_id + ", role="
				+ role + ", salary=" + salary + ", department=" + department + "]";
	}
}
