package com.springboot.dao;

import java.util.List;

import com.springboot.entity.OfficeLocation;
import com.springboot.entity.Project;

public interface ProjectDAO {

	public Long saveProject(Project p);

	public Long saveOffice(OfficeLocation o);

	public List<Project> getAllProjects();

	// public List<Project> getProjectsByLocation(String location);
	public Project findProjectById(long id);
	// public Project updateProject(Project p, long id);
	// public String deleteProject(Project p);
	// public void deleteProjectById(long id);

}
