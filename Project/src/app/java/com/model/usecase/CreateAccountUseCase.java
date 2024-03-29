package app.java.com.model.usecase;

import java.util.ArrayList;

import app.java.com.model.Exceptions.InsertException;
import app.java.com.model.database.api.InsertCommand;
import app.java.com.presenter.interfaces.CreateAccountResultInterface;

public class CreateAccountUseCase implements UseCase {

	private String name;
	private String password;
	private String accountType; // 'T' or 'A'
	private CreateAccountResultInterface resultInterface;

	public CreateAccountUseCase(CreateAccountResultInterface resultInterface, String name,
			String password, String accountType) {
		this.resultInterface = resultInterface;
		this.name = name;
		this.password = password;
		this.accountType = accountType;
	}

	@Override
	public void run() {
		boolean success = false;

		ArrayList<String> attrs = new ArrayList<>();
		attrs.add("userName");
		attrs.add("password");
		attrs.add("accountType");

		ArrayList<String> userData = new ArrayList<>();
		userData.add(this.name);
		userData.add(this.password);
		userData.add(this.accountType);

		InsertCommand ic = new InsertCommand("Account", attrs, userData);

		try {
			success = ic.handle();
		} catch (InsertException e) {
			resultInterface.onErrorCreateAccount("Account already exist.");
		}

		if (success) {
			// NOTE: Need to change this to get pass in account id instead of success
			resultInterface.onSuccessCreateAccount("success");
		}
	}
}
