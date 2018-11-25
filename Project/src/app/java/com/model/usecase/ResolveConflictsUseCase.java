package app.java.com.model.usecase;

import java.util.List;

import app.java.com.model.Exceptions.InsertException;
import app.java.com.model.Exceptions.SelectException;
import app.java.com.model.database.api.InsertCommand;
import app.java.com.model.database.api.SelectCommand;
import app.java.com.presenter.interfaces.ResolveConflictResultInterface;

public class ResolveConflictsUseCase extends UseCase {

	private ResolveConflictResultInterface resolveConflictResultInterface;
	private List<String> userCorrectedValues;
	private String table;

	public ResolveConflictsUseCase(ResolveConflictResultInterface resolveConflictResultInterface,
			List<String> userCorrectedValues, String table) {
		this.resolveConflictResultInterface = resolveConflictResultInterface;
		this.userCorrectedValues = userCorrectedValues;
		this.table = table.replaceAll("`", "");
	}

	@Override
	public void run() {

		List<String> columns = null;
		try {
			columns = new SelectCommand(table).getColumns();
		} catch (SelectException e) {
			e.printStackTrace();
		}

		InsertCommand command = new InsertCommand(table, columns, userCorrectedValues);
		System.out.println("Columns: " + columns);
		System.out.println("User corrected: " + userCorrectedValues);
		try {
			command.insertHandle();
			resolveConflictResultInterface.onSuccessFixingConflict("Success Fixing Error");
		} catch (InsertException e) {
			resolveConflictResultInterface.onErrorFixingConflict(e.getMessage());
		}

	}
}
