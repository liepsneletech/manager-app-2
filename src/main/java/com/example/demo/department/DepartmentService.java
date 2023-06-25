package com.example.demo.department;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class DepartmentService {
	private final DepartmentRepository departmentRepository;
	
	@Autowired
	public DepartmentService(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}

	public List<Department> getDepartments() {
		return departmentRepository.findAll();
	}

	public void addNewDepartment(Department department) {
		departmentRepository.save(department);

		System.out.println(department);
	}

	public void deleteDepartment(Long departmentId) {
		boolean exists = departmentRepository.existsById(departmentId);
		if (!exists) {
			throw new IllegalStateException("Student with id " + departmentId + " does not exist.");
		}
		departmentRepository.deleteById(departmentId);
	}

	@Transactional
	public void updateDepartment(
			Long departmentId, 
			String department_name, 
			Long manager_id
			) 
		{
		Department department = departmentRepository.findById(departmentId)
				.orElseThrow(() -> new IllegalStateException("Department with id " + departmentId + " does not exist."));
		
		if (department_name != null && department_name.length() > 0 && !Objects.equals(department.getDepartment_name(), department_name)) {
			department.setDepartment_name(department_name);
		}
		
		if (manager_id != null && !Objects.equals(department.getManager_id(), manager_id)) {
			department.setManager_id(manager_id);
		}
		
		
	}

}
