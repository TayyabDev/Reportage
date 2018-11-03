package app.java.com.presenter.interfaces;

import app.java.com.view.interfaces.CreateTemplateView;

public interface CreateAccountPresenter {
    void createAccount(String name, String password);

    void attachView(CreateTemplateView view);

    void unbindView();
}
