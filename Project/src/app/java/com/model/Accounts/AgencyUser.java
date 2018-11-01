package app.java.com.model.Accounts;

public abstract class AgencyUser extends UserAbstract {
	public String[] permission = "Agency";
	
	public AgencyUser(String[] username) {
		super(username);
	}
	
	@Override
    public String[] getPermissions() {
    	return this.permission;
    }

}