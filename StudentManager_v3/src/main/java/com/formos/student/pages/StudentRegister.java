package com.formos.student.pages;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.PasswordField;
import org.apache.tapestry5.corelib.components.Select;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.formos.student.entities.Student;
import com.formos.student.services.IStudentService;

public class StudentRegister {
	@Persist
	@Property
	private Student student;
	
	@Property
	private String cfPassword;
	
    public enum Sex {
        Male, Female
    }
	
	@Inject
	private IStudentService service;
	
	@Component(id = "fRegStudent")
	private Form fRegStudent;
	
	@InjectPage
	private StudentList stdList;
	
	@Component(id = "studentID")
	private TextField studentIDField;
	
	@Component(id = "stdPassword")
	private PasswordField stdPassField;
	
	@Component(id = "cfPassword")
	private PasswordField cfPassField;
	
	@Component(id = "lastName")
	private TextField lastNameField;
	
	@Component(id = "sex")
	private Select sexField;
	
	void setupRender() {
		if(student == null){
			student = new Student();
		}
	}
	
	@CommitAfter
	private void saveStudent(){
		// do whatever; 
		service.save(student);
	  	this.student = new Student();
	}
	
	void onValidateFromFRegStudent() {
		if(student.getStudentID() == null || student.getStudentID().equals("")){
			fRegStudent.recordError(studentIDField, "Student ID is required.");
		}
		if(student.getPassword() == null || student.getPassword().equals("")){
			fRegStudent.recordError(stdPassField, "Password is required.");
		}
		if(cfPassword == null || cfPassword.equals("")){
			fRegStudent.recordError(cfPassField, "Please retype your password.");
		}else if(!(cfPassword.equals(student.getPassword()))){
			fRegStudent.recordError(stdPassField, "Password mismatch.");
			fRegStudent.recordError(cfPassField, "Password mismatch.");
		}
		if(student.getLastName() == null || student.getLastName().equals("")){
			fRegStudent.recordError(lastNameField, "Student last name is required.");
		}
		if(student.getSex() == null){
			fRegStudent.recordError(sexField, "Please choose a sex value.");
		}
	}
	
	Object onSuccess() {
		this.saveStudent();
		return stdList;
    }
}
