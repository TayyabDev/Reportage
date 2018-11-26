package test.java.com.model.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import app.java.com.model.database.api.SelectCommand;
import app.java.com.model.usecase.FetchUserDataUseCase;
import app.java.com.model.usecase.UseCase;
import app.java.com.presenter.interfaces.FetchUserDataResultInterface;

public class FetchUserDataUseCaseTest implements FetchUserDataResultInterface {

	private String message;
	private List<List<String>> data;
	private List<String> columns;

	private static List<List<String>> expectedData;
	private static List<String> expectedColumns;


	@BeforeAll
	public static void BeforeAll() {
		SelectCommand selectCommand = new SelectCommand("Account");
		try {
			expectedData = selectCommand.selectHandle();
			expectedColumns = selectCommand.getColumns();
		} catch (Exception e) {
			return;
		}
	}

	@Override
	public void onSuccessSelectTable(List<String> columns, List<List<String>> data) {
		this.columns = columns;
		this.data = data;
	}

	@Override
	public void onErrorSelectTable(String message) {
		this.message = message;
	}

	@Override
	public void onSuccessUpdate(String message) {
		this.message = message;
	}

	@Override
	public void onErrorUpdate(String message) {
		this.message = message;
	}

	@Test
	public void testSuccessSelectTableData() {
		UseCase useCase = new FetchUserDataUseCase(this, "Account");
		useCase.run();
		assertEquals(expectedData, data);
		assertEquals(expectedColumns, columns);
	}

	@Test
	public void testErrorSelect() {
		UseCase useCase = new FetchUserDataUseCase(this, "Account");
		useCase.run();
		assertEquals(null, message);
	}

	@Test
	public void testErrorUpdate() {
		UseCase useCase = new FetchUserDataUseCase(this, "Account");
		useCase.run();
		assertEquals(null, message);
	}

	@Test
	public void testSuccessUpdate() {
		UseCase useCase = new FetchUserDataUseCase(this, "Account");
		useCase.run();
		assertEquals(null, message);
	}

}
