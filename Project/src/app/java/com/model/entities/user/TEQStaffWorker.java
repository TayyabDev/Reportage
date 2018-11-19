package app.java.com.model.entities.user;

import java.util.Date;

import app.java.com.model.entities.Type;

public class TEQStaffWorker extends User {

//    private String teqWorkerId;

	private final String userType = Type.TEQ.getType();
	
    public TEQStaffWorker(String firstName, String lastName, String teqWorkerId) {
    	super(firstName, lastName);
//    	this.teqWorkerId = teqWorkerId;
    }
    
    public TEQStaffWorker(String firstName, String lastName, Date dob) {
    	super(firstName, lastName, dob);
//    	this.teqWorkerId = teqWorkerId;
    }

//    public String getTeqWorkerId() {
//        return teqWorkerId;
//    }

	@Override
	public String getUserType() {
		return this.userType;
	}

}
