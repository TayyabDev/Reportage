package app.java.com.presenter.interfaces;

import app.java.com.model.entities.account.Account;

public interface LoginResultInterface {
    void onSuccessLogin(Account account);
    void onErrorLogin();
}
