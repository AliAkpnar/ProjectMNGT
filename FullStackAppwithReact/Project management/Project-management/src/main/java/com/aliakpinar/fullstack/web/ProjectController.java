package com.aliakpinar.fullstack.web;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.executable.ValidateOnExecution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.diagnostics.FailureAnalysis;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
}
