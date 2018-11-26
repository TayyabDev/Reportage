package test.java.com.model.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import app.java.com.model.entities.account.Account;
import app.java.com.model.usecase.UseCase;
import app.java.com.model.usecase.VerifyAccountUseCase;
import app.java.com.presenter.interfaces.LoginResultInterface;

public class VerifyAccountUseCaseTest implements LoginResultInterface {
	private String KNOWN_USER = "bob";
	private String KNOWN_PASS = "abc";

	boolean status;

	@Test
	public void testBlankUserName() {
		UseCase useCase = new VerifyAccountUseCase(this, "", "pass");
		useCase.run();
		assertEquals(status, false);
	}

	@Test
	public void testBlankPassword() {
		UseCase useCase = new VerifyAccountUseCase(this, "bob", "");
		useCase.run();
		assertEquals(status, false);
	}

	@Test
	public void testBlankUserNameAndPassword() {
		UseCase useCase = new VerifyAccountUseCase(this, "", "");
		useCase.run();
		assertEquals(status, false);
	}

	@Test
	public void testKnownLogin() {
		UseCase useCase = new VerifyAccountUseCase(this, KNOWN_USER, KNOWN_PASS);
		useCase.run();
		assertEquals(status, true);
	}

	@Override
	public void onSuccessLogin(Account account) {
		this.status = true;
	}

	@Override
	public void onErrorLogin() {
		this.status = false;
	}
}
