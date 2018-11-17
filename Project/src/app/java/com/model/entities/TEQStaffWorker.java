package app.java.com.model.entities;

public class TEQStaffWorker extends User {

    private String teqWorkerId;

    public TEQStaffWorker(String firstName, String lastName, String teqWorkerId) {
        setFirstName(firstName);
        setLastName(lastName);
        this.teqWorkerId = teqWorkerId;
    }

    public String getTeqWorkerId() {
        return teqWorkerId;
    }

}
