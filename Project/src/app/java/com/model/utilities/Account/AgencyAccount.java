package app.java.com.model.utilities.Account;

public class AgencyAccount extends Account implements AccountInterface{

	private final String accountType = "A";
	
	public AgencyAccount(int accountId, String userName, String password, boolean registered) {
		super(accountId, userName, password, registered);
	}
	
	public String getAccountType() {
		return this.accountType;
	}
}
