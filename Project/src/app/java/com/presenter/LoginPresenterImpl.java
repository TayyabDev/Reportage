package app.java.com.presenter;

import app.java.com.model.entities.account.Account;
import app.java.com.model.entities.account.AccountTypeFinder;
import app.java.com.model.entities.account.AgencyAccount;
import app.java.com.model.entities.account.TeqAccount;
import app.java.com.model.usecase.VerifyAccountUseCase;
import app.java.com.presenter.interfaces.LoginPresenter;
import app.java.com.presenter.interfaces.LoginResultInterface;
import app.java.com.view.interfaces.LoginView;

public class LoginPresenterImpl implements LoginPresenter, LoginResultInterface {

    private LoginView view;

    public LoginPresenterImpl(LoginView view){
        this.view = view;
    }

    @Override
    public void onSuccessLogin(Account account) {
        if (AccountTypeFinder.isTeqAccount(account)) {
        	view.onSuccessLogin(((TeqAccount) account));
        } else {
        	view.onSuccessLogin(((AgencyAccount) account));
        }
    	

    }

    @Override
    public void onErrorLogin() {
        view.onErrorLogin();

    }

    @Override
    public void verifyAccount(String username, String password) {
        if(view.isFieldsValid(username, password)){
            VerifyAccountUseCase useCase = new VerifyAccountUseCase(this, username,password);
            useCase.run();
        } else {
            view.invalidFields();
        }
    }

    @Override
    public void attachView(LoginView view) {
        this.view = view;

    }

    @Override
    public void unbindView() {
        this.view = null;

    }
}
