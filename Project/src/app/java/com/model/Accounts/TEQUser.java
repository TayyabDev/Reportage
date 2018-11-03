package app.java.com.model.Accounts;

public abstract class TEQUser extends UserAbstract {
	public String[] permission = "TEQ";
	
	public AgencyUser(String[] username) {
		super(username);
	}
	
	@Override
    public String[] getPermissions() {
    	return this.permission;
    }

}