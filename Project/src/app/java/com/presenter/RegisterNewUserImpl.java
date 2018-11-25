package app.java.com.presenter;

import app.java.com.model.entities.account.Account;
import app.java.com.model.entities.user.User;
import app.java.com.model.usecase.RegisterNewUserUseCase;
import app.java.com.model.usecase.UseCase;
import app.java.com.presenter.interfaces.RegisterNewUserPresenter;
import app.java.com.presenter.interfaces.RegisterNewUserResultInterface;
import app.java.com.view.interfaces.RegisterNewUserView;

public class RegisterNewUserImpl
		implements RegisterNewUserPresenter, RegisterNewUserResultInterface {

	private RegisterNewUserView view;

	public RegisterNewUserImpl(RegisterNewUserView view) {
		this.view = view;
	}

	@Override
	public void onSuccessRegisterNewUser(String message) {
		view.onSuccessRegisterNewUser();
	}

	@Override
	public void onErrorRegisterNewUser(String message) {
		view.onErrorRegisterNewUser();
	}

	@Override
	public void registerNewUser(User newUser, Account account) {
		UseCase usecase = new RegisterNewUserUseCase(this, newUser, account);
		usecase.run();
	}

	@Override
	public void attachView(RegisterNewUserView view) {
		this.view = view;

	}

	@Override
	public void unbindView() {
		this.view = null;

	}


}
