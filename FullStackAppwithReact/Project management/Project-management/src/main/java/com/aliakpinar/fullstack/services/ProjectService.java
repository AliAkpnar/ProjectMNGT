package com.aliakpinar.fullstack.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliakpinar.fullstack.entity.Project;
import com.aliakpinar.fullstack.exceptions.ProjectIdException;
import com.aliakpinar.fullstack.repositories.ProjectRepository;

@Service
public class ProjectService {
	
	
	private ProjectRepository projectRepository;
	
	@Autowired
	public ProjectService(ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}
	
	public Project saveOrUpdateProject(Project project) {
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			return projectRepository.save(project);
		} catch (Exception e) {
			throw new ProjectIdException("Project ID :"+project.getProjectIdentifier().toUpperCase() + "  already exists");
		}
		
	}

}
