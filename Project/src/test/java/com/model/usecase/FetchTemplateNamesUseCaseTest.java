package test.java.com.model.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import app.java.com.model.Exceptions.SelectException;
import app.java.com.model.usecase.FetchTemplateNamesUseCase;
import app.java.com.model.usecase.UseCase;
import app.java.com.presenter.interfaces.FetchTemplateNamesResultInterface;

public class FetchTemplateNamesUseCaseTest implements FetchTemplateNamesResultInterface {

	private List<String> expectedTemplateNames = null;

	private List<String> actualTemplateNames =
			Arrays.asList("Client Profile", "Community Connections", "Information and Orientation",
					"Language Training - Course Setup", "Employment Related Services",
					"Language Training - Client Exit");

	@Override
	public void onSuccessFetchingNames(List<String> names) throws SelectException {
		expectedTemplateNames = names;
	}

	@Override
	public void onErrorFetchingNames(String errorMessage) {
		System.out.println(errorMessage); // testing purposes
	}

	@Test
	public void testFetchTemplateNames() {

		UseCase useCase = new FetchTemplateNamesUseCase(this);

		useCase.run();

		// asserting if the verifyUseCase returned true or not
		assertEquals(expectedTemplateNames, actualTemplateNames);

	}

}
