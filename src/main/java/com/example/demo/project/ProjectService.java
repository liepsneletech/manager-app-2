package com.example.demo.project;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class ProjectService {
	private final ProjectRepository projectRepository;
	
	@Autowired
	public ProjectService(ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}

	public List<Project> getProjects() {
		return projectRepository.findAll();
	}

	public void addNewProject(Project project) {
		projectRepository.save(project);

		System.out.println(project);
	}

	public void deleteProject(Long projectId) {
		boolean exists = projectRepository.existsById(projectId);
		if (!exists) {
			throw new IllegalStateException("Project with id " + projectId + " does not exist.");
		}
		projectRepository.deleteById(projectId);
	}

	@Transactional
	public void updateProject(
			Long projectId, 
			String project_name,
			LocalDate project_due_date
			) 
	{
		Project project = projectRepository.findById(projectId)
				.orElseThrow(() -> new IllegalStateException("Project with id " + projectId + " does not exist."));
		
		if (project_name != null && project_name.length() > 0 && !Objects.equals(project.getProject_name(), project_name)) {
			project.setProject_name(project_name);
		}
		
		if (project_due_date != null && !Objects.equals(project.getProject_due_date(), project_due_date)) {
			if(!project_due_date.isAfter(project.getProject_start_date())) {
				throw new IllegalStateException("The due date can't be earlier than start date.");
			} 
			else if (project_due_date.isAfter(project.getProject_start_date().plusYears(1))) {
				throw new IllegalStateException("The due date can't be later than +1 year.");
			}
			else {
				project.setProject_due_date(project_due_date);
			}
				
		}
	}

}


