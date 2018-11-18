package app.java.com.model.entities.account;

import app.java.com.model.entities.Type;

public class AccountTypeFinder {

	public static boolean isTeqAccount(Account account) {
		if (account.getAccountType().compareTo(Type.TEQ.getType()) == 0) {
			return true;
		} else {
			return false;
		}
	}
}
