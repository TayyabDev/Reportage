package app.java.com.presenter;

import app.java.com.model.usecase.CreateAccountUseCase;
import app.java.com.presenter.interfaces.CreateAccountPresenter;
import app.java.com.presenter.interfaces.CreateAccountResultInterface;
import app.java.com.view.interfaces.CreateAccountView;

public class CreateAccountPresenterImpl implements CreateAccountPresenter, CreateAccountResultInterface {

    private CreateAccountView view;




    @Override
    public void createAccount(String name, String password) {
        if(view.isFieldsValid(name, password)){
            CreateAccountUseCase useCase = new CreateAccountUseCase(this, name, password);
            useCase.run();
        } else {
            // should make new method telling user fields are invalid
            view.invalidFields();
        }
    }

    @Override
    public void attachView(CreateAccountView view) {
        this.view = view;

    }

    @Override
    public void unbindView() {
        this.view = null;

    }

    @Override
    public void onSuccessCreateAccount(String message) {
        view.onSuccessAccountCreated();

    }

    @Override
    public void onErrorCreateAccount(String message) {
        view.onErrorCreatingAccount();
    }
}
