package app.java.com.presenter.interfaces;

import app.java.com.model.utilities.Account.Account;

public interface LoginResultInterface {
    void onSuccessLogin(Account account);
    void onErrorLogin();
}
