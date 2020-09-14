package com.springboot.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.springboot.entity.OfficeLocation;
import com.springboot.entity.Project;

@SuppressWarnings("deprecation")
@Repository
@Transactional
@Service("projectDao")
public class ProjectDAOImpl implements ProjectDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Long saveProject(Project p) {
		Long id = (Long) getSession().save(p);
		System.out.println("project ID=" + id);
		return id;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> getAllProjects() {
		List<Project> projList = getSession().createQuery("from com.springboot.entity.Project").list();
		System.out.println(projList);
		return projList;
	}

	@Override
	public Project findProjectById(long id) {
		return getSession().get(Project.class, id);
	}

	/*
	 * @SuppressWarnings("unchecked")
	 * 
	 * @Override public List<Project> getProjectsByLocation(String location) {
	 * List<Office> projList = getSession().createQuery(
	 * "from Office o where o.officeLocation =" + location).list(); // converting
	 * list of Project then sending. System.out.println(projList); return null; }
	 */

	@Override
	public Long saveOffice(OfficeLocation o) {
		/*
		 * OfficeLocation res = (OfficeLocation) getSession().createQuery(
		 * "from com.springboot.entity.OfficeLocation o where o.officeLocation = " +
		 * o.getOfficeLocation());
		 */

		@SuppressWarnings("rawtypes")
		Query query = getSession().createQuery("from OfficeLocation where officeLocation = :officeLocation");
		query.setParameter("officeLocation", o.getOfficeLocation());
		OfficeLocation res = (OfficeLocation) query.uniqueResult();

		if (res == null)
			return (Long) getSession().save(o);
		else
			return null;
	}

	/*
	 * @Override public String deleteProject(Project p) { getSession().delete(p);
	 * return "Project deleted successfully"; }
	 * 
	 * @Override public void deleteProjectById(long id) { Session session =
	 * sessionFactory.getCurrentSession(); Project p = session.load(Project.class,
	 * id); getSession().delete(p); }
	 * 
	 */
	/*
	 * @Override public Project updateProject(Project val, long id) { Session
	 * session = sessionFactory.getCurrentSession(); Project p =
	 * session.get(Project.class, id); p.setName(val.getName());
	 * p.setCity(val.getCity()); session.update(p); return p; }
	 */
}
