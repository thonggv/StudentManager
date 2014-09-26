package com.formos.student.pages;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.Select;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import com.formos.student.entities.Student;
import com.formos.student.services.IStudentService;

public class StudentEdit {
	@Persist
	@Property
	private Student student;

	@Inject
	private IStudentService service;
	
	@InjectPage
	private StudentPassChange changePassPage;

	@Component(id = "fStudent")
	private Form fStudent;

	@InjectPage
	private StudentList stdList;

	@Component(id = "studentID")
	private TextField studentIDField;

	@Component(id = "lastName")
	private TextField lastNameField;
	
	@Component(id = "sex")
	private Select sexField;

	void setupRender() {
		if (student == null) {
			student = new Student();
		}
	}
	
	void setupEditPage(Student std){
		this.student = std;
	}

	@CommitAfter
	private void saveStudent() {
		service.save(student);
		this.student = new Student();
	}

	void onValidateFromFStudent() {
		if (student.getStudentID() == null || student.getStudentID().equals("")) {
			fStudent.recordError(studentIDField, "Student ID is required.");
		}
		if (student.getLastName() == null || student.getLastName().equals("")) {
			fStudent.recordError(lastNameField,
					"Student last name is required.");
		}
		if(student.getSex() == null){
			fStudent.recordError(sexField, "Please choose a sex value.");
		}
	}
	
	Object onActionFromchangePassword(){
		changePassPage.setupPassChangePage(student);
		return changePassPage;
	}

	Object onSuccess() {
		this.saveStudent();
		return stdList;
	}
}
