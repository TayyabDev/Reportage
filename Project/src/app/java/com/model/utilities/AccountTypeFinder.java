package app.java.com.model.utilities;

import app.java.com.model.utilities.Account.Account;
import app.java.com.model.utilities.Account.TeqAccount;

public class AccountTypeFinder {

	public static boolean isTeqAccount(Account account) {
		if (account instanceof TeqAccount) {
			return true;
		} else {
			return false;
		}
	}
}
