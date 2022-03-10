package com.quiz.model;

import java.sql.Date;

public class UserResponse {
	private String FirstName;
	private String LastName;
	private String UserName;
	private String EmailId;

	private Date Dob;
	private String State;
	private String City;
	private String Address;
	private String Gender;
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getEmailId() {
		return EmailId;
	}
	public void setEmailId(String emailId) {
		EmailId = emailId;
	}
	public Date getDob() {
		return Dob;
	}
	public void setDob(Date dob) {
		Dob = dob;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	@Override
	public String toString() {
		return "UserRequest [FirstName=" + FirstName + ", LastName=" + LastName + ", UserName=" + UserName
				+ ", EmailId=" + EmailId + ", Dob=" + Dob + ", Country=" + State
				+ ", City=" + City + ", Address=" + Address + ", Gender=" + Gender + "]";
	}
	public UserResponse() {}

}
