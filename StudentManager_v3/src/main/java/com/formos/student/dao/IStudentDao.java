package com.formos.student.dao;

import java.util.List;

import com.formos.student.entities.Student;

public interface IStudentDao {
	public List<Student> getAllStudents();

	public Student find(long id);
	
	public Student findStdByStdID(String stdID); dsada
	
	public void insert(Student entity);
	
	public void update(Student entity);

	public void delete(Student entity);
}
