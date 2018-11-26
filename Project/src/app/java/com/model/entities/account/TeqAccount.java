package app.java.com.model.entities.account;

import app.java.com.model.entities.Type;

public class TeqAccount extends Account {

	private final String accountType = Type.TEQ.getType();

	public TeqAccount(int accountId, String userName, String password, boolean registered) {
		super(accountId, userName, password, registered);
	}

	@Override
	public String getAccountType() {
		return this.accountType;
	}
}
