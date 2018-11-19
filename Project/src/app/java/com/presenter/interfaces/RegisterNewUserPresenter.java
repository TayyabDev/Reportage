package app.java.com.presenter.interfaces;

import app.java.com.model.entities.account.Account;
import app.java.com.model.entities.user.User;
import app.java.com.view.interfaces.CreateAccountView;
import app.java.com.view.interfaces.RegisterNewUserView;

public interface RegisterNewUserPresenter {

	void registerNewUser(User newUser, Account account);

    void attachView(RegisterNewUserView view);

    void unbindView();
}
