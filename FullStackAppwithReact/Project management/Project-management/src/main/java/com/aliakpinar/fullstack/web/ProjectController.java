package com.aliakpinar.fullstack.web;



import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


import com.aliakpinar.fullstack.entity.Project;
import com.aliakpinar.fullstack.services.MapValidationErrorService;
import com.aliakpinar.fullstack.services.ProjectService;

@RestController
@RequestMapping("/v1/project")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired 
	private MapValidationErrorService mapValidationService;

	
	@PostMapping
	public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project , BindingResult result){
	
		ResponseEntity<?> errorMap = mapValidationService.MapValidationService(result);
		if (errorMap != null) {
			return errorMap;
		}
		
		Project project1 = projectService.saveOrUpdateProject(project); // veri tabanına kaydet
		return new ResponseEntity<Project>(project1,HttpStatus.CREATED);
	}
	
	@GetMapping("/{projectId}")
	public ResponseEntity<?> getProjectById(@PathVariable String projectId){
		Project project = projectService.findProjectById(projectId);
		return new ResponseEntity<Project>(project, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public Iterable<Project> getAllProjects(){
		return projectService.findAllProject();
	}
	
	@DeleteMapping("/{projectId}")
	public ResponseEntity<?> deleteProject(@PathVariable("projectId") String projectId){
		projectService.deleteProjectById(projectId);
		return new ResponseEntity<String>("Project with ID : " +projectId + " - was deleted" , HttpStatus.OK);
	}

}
