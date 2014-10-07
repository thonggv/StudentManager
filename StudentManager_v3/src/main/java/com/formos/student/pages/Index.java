package com.formos.student.pages;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.*;
import org.apache.tapestry5.ioc.annotations.Inject;
import com.formos.student.services.IStudentService;


/**
 * Start page of application StudentManager.
 */
public class Index
{	
    @Property
    private String studentLoginID;

    @Property
    private String studentLoginPass;
	
	@Component(id = "fLogin")
	private Form fLogin;
	
	@Component(id = "studentLoginID")
	private TextField studentIDField;

	@Component(id = "studentLoginPass")
	private PasswordField studentPassField;
	
	@InjectPage
	private StudentList stdList;
	
	@Inject
	private IStudentService service;
	
    void onValidateFromFLogin() {
        if (studentLoginID == null || studentLoginID.trim().equals("")) {
        	fLogin.recordError(studentIDField, "Student ID is required.");
        }
        if (studentLoginPass == null || studentLoginPass.trim().equals("")) {
        	fLogin.recordError(studentPassField, "Please enter your password");
        }
    }
    
    Object onSuccess() {
    	if(!service.loginValidate(studentLoginID, studentLoginPass)){
    		fLogin.recordError(studentIDField, "Student ID is invalid or bad password");
    		return this;
    	}
    	return stdList;
    }
}
