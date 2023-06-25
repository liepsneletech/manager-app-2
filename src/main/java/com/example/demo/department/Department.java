package com.example.demo.department;

import java.util.List;

import com.example.demo.employee.Employee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table
public class Department {
	@Id
	@SequenceGenerator(name = "department_sequence", sequenceName = "department_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "department_sequence")
	private Long department_id;
	private String department_name;
	private Long manager_id;

	@OneToMany(mappedBy = "department", cascade = {CascadeType.DETACH, CascadeType.REFRESH})
	@JsonIgnore
	private List<Employee> list_of_employees;

	public Department() {
	}

	public Department(String department_name, Long manager_id) {
		this.department_name = department_name;
		this.manager_id = manager_id;
	}

	public Department(Long department_id, String department_name, Long manager_id) {
		this.department_id = department_id;
		this.department_name = department_name;
		this.manager_id = manager_id;
	}

	public Long getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(Long department_id) {
		this.department_id = department_id;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public Long getManager_id() {
		return manager_id;
	}

	public void setManager_id(Long manager_id) {
		this.manager_id = manager_id;
	}

	public List<Employee> getList_of_employees() {
		return list_of_employees;
	}

	public void setList_of_employees(List<Employee> list_of_employees) {
		this.list_of_employees = list_of_employees;
	}

	@Override
	public String toString() {
		return "Department [department_id=" + department_id + ", department_name=" + department_name + ", manager_id="
				+ manager_id + "]";
	}
}
