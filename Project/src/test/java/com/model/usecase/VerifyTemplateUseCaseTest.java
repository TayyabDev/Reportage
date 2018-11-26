package test.java.com.model.usecase;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import app.java.com.model.usecase.UseCase;
import app.java.com.model.usecase.VerifyTemplateUseCase;
import app.java.com.model.utilities.templateFile.TemplateFileInterface;
import app.java.com.presenter.interfaces.VerifyTemplateResultInterface;
import test.java.com.model.utilities.ClientProfileDifferentColumnsSizeMockFile;
import test.java.com.model.utilities.ClientProfileMockFile;
import test.java.com.model.utilities.ClientProfileSameColumnsSizeDifferentValuesMockFile;
import test.java.com.model.utilities.MockTemplate;

public class VerifyTemplateUseCaseTest implements VerifyTemplateResultInterface {

	private static final String templateName = "Client Profile";
	private boolean testResult = false;

	@Override
	public void onTemplateSelectedCompatible(boolean templateValid,
			TemplateFileInterface fileInterface) {
		testResult = templateValid;
	}

	@Test
	public void testVerifyUseCaseWithDifferentSizedColumns() {

		UseCase useCase = new VerifyTemplateUseCase(this,
				new ClientProfileDifferentColumnsSizeMockFile(new MockTemplate()), templateName);

		useCase.run();

		assertFalse(testResult);

	}

	@Test
	public void testVerifyUseCaseWithSameColumnsSizeMismatchingValues() {
		UseCase useCase = new VerifyTemplateUseCase(this,
				new ClientProfileSameColumnsSizeDifferentValuesMockFile(new MockTemplate()),
				templateName);
		useCase.run();

		assertFalse(testResult);
	}

	@Test
	public void testVerifyUseCaseWithSameSizeMatchingValues() {

		UseCase useCase = new VerifyTemplateUseCase(this,
				new ClientProfileMockFile(new MockTemplate()), templateName);
		useCase.run();

		assertTrue(testResult);

	}

}
