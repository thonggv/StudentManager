package com.formos.student.services;

import java.util.List;

import com.formos.student.entities.Student;

public interface IStudentService {
	public List<Student> getAllStudents();

	public Student find(long id);
	
	public Student findStdByStdID(String stdID);
	
	public void save(Student student);

	public void delete(long id);

	public boolean loginValidate(String studentID, String studentPass);
}
