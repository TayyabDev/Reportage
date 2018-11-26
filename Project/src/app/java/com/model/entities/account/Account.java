package app.java.com.model.entities.account;

public abstract class Account {

	protected int accountId;
	protected String userName;
	protected String password;
	protected boolean registered;

	public Account(int accountId, String userName, String password, boolean registered) {
		this.accountId = accountId;
		this.userName = userName;
		this.password = password;
		this.registered = registered;
	}

	public String getUserName() {
		return userName;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isRegisterd() {
		return this.registered;
	}

	public void setRegisterd(boolean registerd) {
		this.registered = registerd;
	}

	public abstract String getAccountType();

	@Override
	public String toString() {
		String res = "username: "
				+ this.userName
				+ " password: "
				+ this.password
				+ " registered: "
				+ this.registered;
		return res;
	}
}
