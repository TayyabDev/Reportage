package app.java.com.presenter;

import app.java.com.presenter.interfaces.CreateAccountPresenter;
import app.java.com.presenter.interfaces.CreateAccountResultInterface;
import app.java.com.view.interfaces.CreateAccountView;
import app.java.com.view.interfaces.CreateTemplateView;

public class CreateAccountPresenterImpl implements CreateAccountPresenter, CreateAccountResultInterface {

   

    @Override
    public void createAccount(String name, String password) {

    }

    @Override
    public void attachView(CreateTemplateView view) {

    }

    @Override
    public void unbindView() {

    }

    @Override
    public void onSuccessCreateAccount(String message) {

    }

    @Override
    public void onErrorCreateAccount(String message) {

    }
}
