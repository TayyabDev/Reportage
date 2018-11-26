package app.java.com.model.entities.user;

import java.util.Date;

public abstract class User {

	protected String firstName;
	protected String lastName;
	protected Date dob;
	protected int accountId;
	protected int userId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	protected User(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	protected User(String firstName, String lastName, Date dob) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
	}

	public abstract String getUserType();

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Date getDob() {
		return this.dob;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return this.lastName;
	}



}
