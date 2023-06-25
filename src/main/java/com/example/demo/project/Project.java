package com.example.demo.project;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table
public class Project {
	@Id
	@SequenceGenerator(name = "project_sequence", sequenceName = "project_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_sequence")
	private Long project_id;
	private String project_name;
	@Column(nullable = false, updatable = false)
	private LocalDate project_start_date = LocalDate.now();
	private LocalDate project_due_date;
	
	public Project() {
	}
	
	public Project(String project_name, LocalDate project_due_date) {
		this.project_name = project_name;
		this.project_due_date = project_due_date;
	}
	
	public Project(Long project_id, String project_name, LocalDate project_due_date) {
		this.project_id = project_id;
		this.project_name = project_name;
		this.project_due_date = project_due_date;
	}
	
	public Long getProject_id() {
		return project_id;
	}

	public void setProject_id(Long project_id) {
		this.project_id = project_id;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public LocalDate getProject_start_date() {
		return project_start_date;
	}

	public void setProject_start_date(LocalDate project_start_date) {
		this.project_start_date = project_start_date;
	}

	public LocalDate getProject_due_date() {
		return project_due_date;
	}

	public void setProject_due_date(LocalDate project_due_date) {
		this.project_due_date = project_due_date;
	}

	@Override
	public String toString() {
		return "Project [project_id=" + project_id + ", project_name=" + project_name + ", project_start_date="
				+ project_start_date + ", project_due_date=" + project_due_date + "]";
	}

}
