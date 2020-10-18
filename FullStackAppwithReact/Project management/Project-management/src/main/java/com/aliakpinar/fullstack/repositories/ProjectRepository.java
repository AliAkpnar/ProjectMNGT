package com.aliakpinar.fullstack.repositories;

import org.springframework.data.repository.CrudRepository;

import com.aliakpinar.fullstack.entity.Project;

public interface ProjectRepository extends CrudRepository<Project, Long> {
	
	Iterable<Project> findAllById(Iterable<Long> id);

}
