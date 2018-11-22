package test.java.com.model.utilities;

import app.java.com.model.entities.account.Account;

public class MockAccount extends Account {

    public MockAccount(int accountId, String userName, String password, boolean registered) {
        super(accountId, userName, password, registered);
    }

    @Override
    public String getAccountType() {
        return "MockAccount";
    }
}
