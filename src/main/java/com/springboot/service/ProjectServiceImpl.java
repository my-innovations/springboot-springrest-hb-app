package com.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.dao.ProjectDAO;
import com.springboot.entity.OfficeLocation;
import com.springboot.entity.Project;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	ProjectDAO projectDao;

	@Override
	public void saveProject(Project p) {
		projectDao.saveProject(p);
	}

	@Override
	public List<Project> getAllProjects() {
		return projectDao.getAllProjects();
	}

	@Override
	public Project getProjectById(long projid) {
		return projectDao.findProjectById(projid);

	}

	@Override
	public List<Project> getProjectsByLocation(String location) {
		return null;
	}

	@Override
	public Long saveOffice(OfficeLocation office) {
		return projectDao.saveOffice(office);
	}

}
