package com.springboot.service;

import java.util.List;

import com.springboot.entity.OfficeLocation;
import com.springboot.entity.Project;

public interface ProjectService {

	public void saveProject(Project p);

	public List<Project> getAllProjects();

	public Project getProjectById(long projid);

	public List<Project> getProjectsByLocation(String location);

	public Long saveOffice(OfficeLocation office);

}
