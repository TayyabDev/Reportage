package app.java.com.presenter.interfaces;

import app.java.com.view.interfaces.CreateAccountView;

public interface CreateAccountPresenter {
    void createAccount(String name, String password);

    void attachView(CreateAccountView view);

    void unbindView();
}
