package test.java.com.model.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;

import app.java.com.model.database.api.SelectCommand;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import app.java.com.model.Exceptions.SelectException;
import app.java.com.model.usecase.FetchTemplateNamesUseCase;
import app.java.com.model.usecase.UseCase;
import app.java.com.presenter.interfaces.FetchTemplateNamesResultInterface;

public class FetchTemplateNamesUseCaseTest implements FetchTemplateNamesResultInterface {

    private static final String TEMPLATE_TABLE = "Template";
	private static List<String> expectedTemplateNames = null;

	private static List<String> actualTemplateNames = new ArrayList<>();

	@BeforeAll
    static void setup() throws SelectException {
        List<String> target = new ArrayList<>();
        target.add("templateName");

        SelectCommand selectCommand = new SelectCommand(target, TEMPLATE_TABLE);
        List<List<String>> result = selectCommand.selectHandle();

        for (List<String> row : result) {
            actualTemplateNames.add(row.get(0));
        }
    }

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
