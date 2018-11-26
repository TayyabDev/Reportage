package app.java.com.model.usecase;

import java.util.ArrayList;
import java.util.List;

import app.java.com.model.Exceptions.SelectException;
import app.java.com.model.database.api.SelectCommand;
import app.java.com.model.entities.account.Account;
import app.java.com.model.entities.account.AgencyAccount;
import app.java.com.model.entities.account.TeqAccount;
import app.java.com.presenter.interfaces.LoginResultInterface;

public class VerifyAccountUseCase implements UseCase {

	private LoginResultInterface resultInterface;
	private String username;
	private String password;
	
	// Account table
	private final String ACCOUNT_TABLE = "Account";
	private final String PASSWORD = "password";
	private final String USERNAME = "userName";
	private final String ACCOUNTID = "accountId";
	private final String ACCOUNT_TYPE = "accountType";
	private final String REGISTERED = "registered";
	

	public VerifyAccountUseCase(LoginResultInterface resultInterface, String username,
			String password) {
		this.resultInterface = resultInterface;
		this.username = username;
		this.password = password;
	}

	@Override
	public void run() {
		if (username.length() == 0 || password.length() == 0) {
			resultInterface.onErrorLogin();
		} else {


			ArrayList<String> attrs = new ArrayList<>();
			attrs.add(ACCOUNTID);
			attrs.add(ACCOUNT_TYPE);
			attrs.add(REGISTERED);

			List<String> constraints = new ArrayList<>();
			constraints.add("`" + USERNAME + "` = '" + this.username + "'");
			constraints.add("`" + PASSWORD+ "` = '" + this.password + "'");

			SelectCommand sel = new SelectCommand(attrs, ACCOUNT_TABLE, constraints);

			List<List<String>> data = null;
			try {
				data = sel.selectHandle();

			} catch (SelectException e) {

			}
			if (data == null || data.isEmpty()) {
				resultInterface.onErrorLogin();
			} else {
				Account account;
				boolean registered = data.get(0).get(2).compareTo("1") == 0;
				int accountId = Integer.parseInt(data.get(0).get(0));
				if (data.get(0).get(1).compareTo("T") == 0) {
					account = new TeqAccount(accountId, username, password, registered);
				} else {
					account = new AgencyAccount(accountId, username, password, registered);
				}
				resultInterface.onSuccessLogin(account);
			}
		}
	}
}
