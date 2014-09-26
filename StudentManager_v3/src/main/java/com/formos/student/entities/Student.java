package com.formos.student.entities;

import java.text.DateFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Student {
	@Id
	@GeneratedValue	
	private Long id;
	
	private String studentID;
	
	private String password;
	
	private String firstName;
	
	private String middleName;
	
	private String lastName;
	
	private String sex;
	
	private boolean married;
	
	private Date birthday;
	
	private String Phone;
	
	private String Address;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStudentID() {
		return studentID;
	}	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public boolean getMarried() {
		return married;
	}

	public void setMarried(boolean married) {
		this.married = married;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}
	
	public String getStudentName() {
		if(firstName == null){
			firstName = "";
		}
		if(middleName == null){
			middleName = "";
		}		
		return firstName + " " + middleName + " " + lastName;
	}
	
	public String getBirthdayString(){
		DateFormat format = DateFormat.getDateInstance(DateFormat.MEDIUM);
		return format.format(birthday);
	}
}
