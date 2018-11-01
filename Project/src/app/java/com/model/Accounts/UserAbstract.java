package app.java.com.model.Accounts;

public abstract class UserAbstract implements UserInterface {
	protected String[] username;
	public String[] permission;
	public String[] email;
	
	public UserAbstract(String[] username) {
		this.username = username;
	}
	
    public String[] getPermissions() {
    	return this.permission;
    }
    
    public String[] getEmail() {
    	return this.email;
    }
    
    public void setEmail(String[] email) {
    	this.email = email;
    }
}
