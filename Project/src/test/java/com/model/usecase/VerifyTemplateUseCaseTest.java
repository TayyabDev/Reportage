package test.java.com.model.usecase;

import app.java.com.model.usecase.UseCase;
import app.java.com.model.usecase.VerifyTemplateUseCase;
import app.java.com.model.utilities.templateFile.TemplateFileInterface;
import app.java.com.presenter.interfaces.VerifyTemplateResultInterface;
import org.junit.jupiter.api.Test;
import test.java.com.model.utilities.ClientProfileDifferentColumnsSizeMockFile;
import test.java.com.model.utilities.ClientProfileMockFile;
import test.java.com.model.utilities.ClientProfileSameColumnsSizeDifferentValuesMockFile;
import test.java.com.model.utilities.MockTemplate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VerifyTemplateUseCaseTest implements VerifyTemplateResultInterface {

    private static final String templateName = "Client Profile";
    private boolean testResult = false;

    @Override
    public void onTemplateSelectedCompatible(boolean templateValid, TemplateFileInterface fileInterface) {
        testResult = templateValid;
    }

    // Test 1 - Ensure that verifyUseCase returns error if the size of columns does not match
    //("Testing VerifyUseCase with columns of different sizes from file and database")
    @Test
    public void testVerifyUseCaseWithDifferentSizedColumns() {

        UseCase useCase = new VerifyTemplateUseCase(this,
                new ClientProfileDifferentColumnsSizeMockFile(new MockTemplate()), templateName);

        useCase.run();

        // asserting if the verifyUseCase returned true or not
        assertFalse(testResult);

    }

    // Test 2 - Ensure that verifyUseCase returns error if any of the column names do not match
    //@DisplayName("Testing VerifyUseCase with columns of different sizes from file and database")
    @Test
    public void testVerifyUseCaseWithSameColumnsSizeMismatchingValues() {
        UseCase useCase = new VerifyTemplateUseCase(this,
                new ClientProfileSameColumnsSizeDifferentValuesMockFile(new MockTemplate()), templateName);
        useCase.run();

        // asserting if the verifyUseCase returned true or not
        assertFalse(testResult);
    }

    // Test 3 - Ensure that verifyUseCase returns success if the size of columns is the same and that they match
    //@DisplayName("Testing VerifyUseCase with columns of different sizes from file and database")
    @Test
    public void testVerifyUseCaseWithSameSizeMatchingValues() {

        UseCase useCase = new VerifyTemplateUseCase(this, new ClientProfileMockFile(new MockTemplate()), templateName);
        useCase.run();

        // asserting if the verifyUseCase returned true or not
        assertTrue(testResult);

    }

    // Ensure that verifyUseCase works for all the above cases with a CSV file as well

}
