package app.java.com.model.entities;

public abstract class User {

    private String firstName;
    private String lastName;

    void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    String getFirstName() {
        return this.firstName;
    }

    void setLastName(String lastName) {
        this.lastName = lastName;
    }

    String getLastName() {
        return this.lastName;
    }

}
