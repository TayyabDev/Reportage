package app.java.com.model.entities.account;

import app.java.com.model.entities.Type;

public class AccountTypeFinder {

	public static boolean isTeqAccount(Account account) {
		return (account.getAccountType().compareTo(Type.TEQ.getType()) == 0);
	}
}
