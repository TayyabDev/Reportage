package app.java.com.model.entities;

import java.util.Date;

public class Officer extends User {

    private String agencyName;
    private Date birthDate;

    public Officer(String firstName, String lastName, Date birthDate, String agencyName) {
        setFirstName(firstName);
        setLastName(lastName);
        this.agencyName = agencyName;
        this.birthDate = birthDate;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public Date getBirthDate() {
        return birthDate;
    }
}
