package app.java.com.presenter.interfaces;

import app.java.com.view.interfaces.LoginView;

public interface LoginPresenter {

    void verifyAccount(String username, String password);

    void attachView(LoginView view);
    void unbindView();
}
