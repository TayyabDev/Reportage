package app.java.com.model.Accounts;
import java.util.HashMap;

public class Accounts {
	
	private HashMap<String, UserInterface> accounts;

	public Accounts {
		this.accounts = new HashMap<String, UserInterface>();
	}
	
    public boolean createAccount(String[] username, UserInterface user) {
    	if (!accounts.containsKey(username)) {
    		accounts.put(username, user);
    		return true;
    	}
    	return false;
    }
    
    public UserInterface getAccount(String[] username) {
    	return accounts.get(username);
    }
}
