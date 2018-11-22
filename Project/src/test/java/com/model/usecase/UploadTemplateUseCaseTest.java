package test.java.com.model.usecase;

import app.java.com.model.usecase.UploadTemplateUseCase;
import app.java.com.model.usecase.UseCase;
import app.java.com.presenter.interfaces.UploadTemplateResultInterface;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;
import test.java.com.model.utilities.ClientProfileMockFile;
import test.java.com.model.utilities.MockAccount;
import test.java.com.model.utilities.MockTemplate;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UploadTemplateUseCaseTest implements UploadTemplateResultInterface {

    private static final String TEMPLATE_NAME = "Client Profile";
    private static final int ACCOUNT_ID = 1;
    private static final String USER_NAME = "Vishwa";
    private static final String PASSWORD = "password";

    private boolean testResult = false;

    @Override
    public void onSuccessUploadingTemplate() {
        testResult = true;
    }

    @Override
    public void onErrorUploadingTemplate(List<Exception> errorMessages) {
        System.out.println("Error uploading template");
    }


    // We can assume that before running this usecase, that our file is verified and valid
    @Test
    public void testUploadTemplateUseCaseUsingNormalMockFile() {

        UseCase useCase = new UploadTemplateUseCase(this, new Date(118, 10, 12),
                                            TEMPLATE_NAME, new ClientProfileMockFile(new MockTemplate()),
                                            new MockAccount(ACCOUNT_ID,USER_NAME,PASSWORD,true));

        useCase.run();

        assertTrue(testResult);
    }
}
