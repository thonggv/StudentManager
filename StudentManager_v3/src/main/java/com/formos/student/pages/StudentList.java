package com.formos.student.pages;

import java.util.List;

import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import com.formos.student.entities.Student;
import com.formos.student.services.IStudentService;

public class StudentList {
	@Property
	private Student std;
	
	private List<Student> studentList;
	
	@Inject
	private IStudentService service;
	
	@InjectPage
	private StudentEdit editPage;
	
	@InjectPage
	private StudentRegister regPage;
	
	public List<Student> getStudentList() {
		studentList = service.getAllStudents(); 
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}
	
	@CommitAfter
	private void deleteStudent(long id){
		service.delete(id);
	}
	
	Object onActionFromRegister(){
		return regPage;
	}
	
	Object onActionFromEdit(long id){
		editPage.setupEditPage(service.find(id));
		return editPage;
	}
	
	Object onActionFromDelete(long id){
		this.deleteStudent(id);
		return this;
	}
}
