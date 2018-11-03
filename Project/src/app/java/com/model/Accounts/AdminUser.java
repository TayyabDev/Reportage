package app.java.com.model.Accounts;

public abstract class AdminUser extends UserAbstract {
	public String[] permission = "Admin";
	
	public AgencyUser(String[] username) {
		super(username);
	}
	
	@Override
    public String[] getPermissions() {
    	return this.permission;
    }

}