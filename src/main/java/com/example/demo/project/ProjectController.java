package com.example.demo.project;

import java.time.LocalDate;
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
@RequestMapping(path = "api/v1/project")
public class ProjectController {
	private final ProjectService projectService;
	
	@Autowired
	public ProjectController(ProjectService projectService) {
		this.projectService = projectService;
	}
	
	@GetMapping()
	public List<Project> getProjects() {
		return projectService.getProjects();
	}

	@PostMapping
	public void registerNewProject(@RequestBody Project project) {
		projectService.addNewProject(project);
	}

	@DeleteMapping(path = "{projectId}")
	public void deleteProject(@PathVariable("employeeId") Long projectId) {
		projectService.deleteProject(projectId);
	}

	@PutMapping(path = "{projectId}")
	public void updateProject(
			@PathVariable("projectId") Long projectId, 
			@RequestParam(required = false) String project_name,
			@RequestParam(required = false) LocalDate project_due_date
			) 
	{
		projectService.updateProject(projectId, project_name, project_due_date);
	}
}

