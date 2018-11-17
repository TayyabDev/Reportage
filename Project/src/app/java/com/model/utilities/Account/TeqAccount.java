package app.java.com.model.utilities.Account;

public class TeqAccount extends Account implements AccountInterface{

	private final String accountType = "T";
	
	public TeqAccount(int accountId, String userName, String password, boolean registered) {
		super(accountId, userName, password, registered);
	}
	
	public String getAccountType() {
		return this.accountType;
	}
}
