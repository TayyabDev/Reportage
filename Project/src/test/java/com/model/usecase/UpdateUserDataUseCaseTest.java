package test.java.com.model.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import app.java.com.model.database.api.SelectCommand;
import app.java.com.model.entities.DataChanges;
import app.java.com.model.usecase.UpdateUserDataUseCase;
import app.java.com.model.usecase.UseCase;
import app.java.com.presenter.interfaces.UpdateUserDataResultInterface;

public class UpdateUserDataUseCaseTest implements UpdateUserDataResultInterface {

	private List<String> columns;
	private List<List<String>> data;
	private List<DataChanges> changesList;
	private String message;

	private static List<String> expectedColumns;
	private static List<List<String>> expectedData;
	private static List<DataChanges> expectedChanges = new ArrayList<DataChanges>();

	private static List<List<String>> original = new ArrayList<List<String>>();
	private static List<List<String>> changed = new ArrayList<List<String>>();
	@BeforeAll
	public static void BeforeAll() {
		original.add(new ArrayList<>());
		changed.add(new ArrayList<>());
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
	public void onShowDataChanges(List<DataChanges> changesList) {
		this.changesList = changesList;
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
	public void testSuccessSelectTable() {
		UseCase useCase = new UpdateUserDataUseCase(this, "Account", original, changed);
		try{useCase.run();}catch(IndexOutOfBoundsException e){return;};
		assertEquals(expectedColumns, columns);
		assertEquals(expectedData, data);
	}

	@Test
	public void testErrorSelectTable() {
		UpdateUserDataUseCase useCase = new UpdateUserDataUseCase(this, "Account", original, changed);
		useCase.runCompare();
		assertEquals(null, message);
	}

	@Test
	public void testShowDataChanges() {
		UpdateUserDataUseCase useCase = new UpdateUserDataUseCase(this, "Account", original, changed);
		useCase.runCompare();
		assertEquals(expectedChanges, changesList);
	}

	@Test
	public void testSuccessUpdate() {
		UpdateUserDataUseCase useCase = new UpdateUserDataUseCase(this, "Account", original, changed);
		try{useCase.run();}catch(IndexOutOfBoundsException e){return;};	
		assertEquals("Unable to select columns", message);
	}

	@Test
	public void testErrorUpdate() {
		UpdateUserDataUseCase useCase = new UpdateUserDataUseCase(this, "Account", original, changed);
		try{useCase.run();}catch(IndexOutOfBoundsException e){return;};
		assertEquals("Unable to select columns", message);
	}
}
