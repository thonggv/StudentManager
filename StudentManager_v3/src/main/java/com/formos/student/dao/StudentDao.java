package com.formos.student.dao;

import java.util.List;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import com.formos.student.entities.Student;

public class StudentDao implements IStudentDao {
	@Inject
	private Session _session;
	
	@SuppressWarnings("unchecked")
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		List<Student> list = (List<Student>)_session.createCriteria(Student.class).list();
		return list;
	}

	public Student find(long id) {
		// TODO Auto-generated method stub
		Criteria c = _session.createCriteria(Student.class).add(Restrictions.eq("id", id));
		return (Student)c.uniqueResult();
	}
	
	public Student findStdByStdID(String stdID) {
		// TODO Auto-generated method stub
		Criteria c = _session.createCriteria(Student.class).add(Restrictions.eq("studentID", stdID));
		return (Student)c.uniqueResult();
	}

	public void insert(Student entity) {
		// TODO Auto-generated method stub
		_session.save(entity);
		_session.flush();
	}

	public void update(Student entity) {
		// TODO Auto-generated method stub
		_session.update(entity);
		_session.flush();
	}

	public void delete(Student entity) {
		// TODO Auto-generated method stub
		_session.delete(entity);
		_session.flush();
	}
}
