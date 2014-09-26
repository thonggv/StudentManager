package com.formos.student.services;

import java.util.List;

import org.apache.tapestry5.ioc.annotations.Inject;
import com.formos.student.dao.IStudentDao;
import com.formos.student.entities.Student;

public class StudentServiceImpl implements IStudentService {
	@Inject
	private IStudentDao dao;
	
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		List<Student> studentList = dao.getAllStudents();
		return studentList;
	}

	public Student find(long id) {
		// TODO Auto-generated method stub
		Student std = dao.find(id);
		return std;
	}
	
	public Student findStdByStdID(String stdID) {
		// TODO Auto-generated method stub
		Student std = dao.findStdByStdID(stdID);
		return std;
	}

	public void save(Student student) {
		// TODO Auto-generated method stub
		if(student.getId() != null && student.getId() > 0 )
		{
			Student std = dao.find(student.getId());	
			if(std != null)
			{
				std.setStudentID(student.getStudentID());
				std.setPassword(student.getPassword());
				std.setFirstName(student.getFirstName());
				std.setMiddleName(student.getMiddleName());
				std.setLastName(student.getLastName());
				std.setSex(student.getSex());
				std.setMarried(student.getMarried());
				std.setBirthday(student.getBirthday());
				std.setPhone(student.getPhone());
				std.setAddress(student.getAddress());
				dao.update(std);
			}			
		}
		else
		{
			Student std = new Student();
			std.setStudentID(student.getStudentID());
			std.setPassword(student.getPassword());
			std.setFirstName(student.getFirstName());
			std.setMiddleName(student.getMiddleName());
			std.setLastName(student.getLastName());
			std.setSex(student.getSex());
			std.setMarried(student.getMarried());
			std.setBirthday(student.getBirthday());
			std.setPhone(student.getPhone());
			std.setAddress(student.getAddress());
			dao.insert(std);
		}		
	}

	public void delete(long id) {
		// TODO Auto-generated method stub
		Student std = dao.find(id);	
		if(std != null)
			dao.delete(std);
	}

	public boolean loginValidate(String studentID, String studentPass) {
		// TODO Auto-generated method stub
		if(findStdByStdID(studentID).getPassword().equals(studentPass)){
			return true;
		}
		return false;
	}
}
