package app.java.com.model.entities.user;

import java.util.Date;

import app.java.com.model.entities.Type;

public class Officer extends User {

	private String agencyName;
	private final String userType = Type.AGENCY.getType();

	public Officer(String firstName, String lastName, String agencyName) {
		super(firstName, lastName);
		this.agencyName = agencyName;
	}

	public Officer(String firstName, String lastName, Date birthDate, String agencyName) {
		super(firstName, lastName, birthDate);
		this.agencyName = agencyName;
	}

	@Override
	public String getUserType() {
		return this.userType;
	}

	public String getAgencyName() {
		return agencyName;
	}
}
