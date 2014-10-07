package com.formos.student.pages;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.PasswordField;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.formos.student.entities.Student;
import com.formos.student.services.IStudentService;

public class StudentPassChange {
	@Persist
	@Property
	private Student student;
	
	@Property
	private String stdPassword;
	
	@Property
	private String newPassword;
	
	@Property
	private String newPasswordRetype;
	
	@Component(id = "stdPassword")
	private PasswordField stdPassField;
	
	@Component(id = "newPassword")
	private PasswordField newPasswordField;
	
	@Component(id = "newPasswordRetype")
	private PasswordField newPasswordRetypeField;
	
	@Inject
	private IStudentService service;
	
	@Component(id = "fStudentPassChange")
	private Form fStudentPassChange;
	
	@InjectPage
	private StudentList stdList;
	
	void setupRender() {
		if (student == null) {
			student = new Student();
		}
	}
	
	void setupPassChangePage(Student std){
		this.student = std;
	}
	
	@CommitAfter
	private void saveStudent(){
		// do whatever; 
		service.save(student);
	  	this.student = new Student();
	}
	
	void onValidateFromFStudentPassChange() {
		
		if(stdPassword == null || stdPassword.equals("")){
			fStudentPassChange.recordError(stdPassField, "Please enter current password.");
		}
		if(newPassword == null || newPassword.equals("")){
			fStudentPassChange.recordError(newPasswordField, "Please enter new password.");
		}
		if(newPasswordRetype == null || newPasswordRetype.equals("")){
			fStudentPassChange.recordError(newPasswordRetypeField, "Retype new password.");
		}else if(!(newPasswordRetype.equals(newPassword))){
			fStudentPassChange.recordError(newPasswordField, "Password mismatch.");
			fStudentPassChange.recordError(newPasswordRetypeField, "Password mismatch.");
		}
	}
	
	Object onSuccess() {		
		if(!service.loginValidate(student.getStudentID(), stdPassword)){
			fStudentPassChange.recordError(stdPassField, "Current password invalid!!!");
    		return this;
    	}
		student.setPassword(newPassword);
		this.saveStudent();
    	return stdList;
    }
}
