package app.java.com.model.entities.account;

import app.java.com.model.entities.Type;

public class AgencyAccount extends Account{

	private final String accountType = Type.AGENCY.getType();
	
	public AgencyAccount(int accountId, String userName, String password, boolean registered) {
		super(accountId, userName, password, registered);
	}
	
	@Override
	public String getAccountType() {
		return this.accountType;
	}
}
