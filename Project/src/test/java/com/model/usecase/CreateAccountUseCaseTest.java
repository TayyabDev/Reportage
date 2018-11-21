package test.java.com.model.usecase;


import app.java.com.model.usecase.CreateAccountUseCase;
import app.java.com.model.usecase.UseCase;
import app.java.com.presenter.interfaces.CreateAccountResultInterface;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateAccountUseCaseTest implements CreateAccountResultInterface {
    private String message;

    @Test
    public void testBlankUserName(){
        UseCase useCase = new CreateAccountUseCase(this, "", "pass", "A");
        useCase.run();
        assertEquals("failure", message);
    }

    @Test
    public void testBlankPassword(){
        UseCase useCase = new CreateAccountUseCase(this, "user", "", "A");
        useCase.run();
        assertEquals("failure", message);
    }

    @Test
    public void testBlankUserNameAndPassword(){
        UseCase useCase = new CreateAccountUseCase(this, "", "", "A");
        useCase.run();
        assertEquals("failure", message);
    }


    @Override
    public void onSuccessCreateAccount(String message) {
        this.message = message;

    }

    @Override
    public void onErrorCreateAccount(String message) {
        this.message = message;
    }
}
