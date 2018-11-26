package app.java.com.model.usecase;

import java.util.ArrayList;
import java.util.List;

import app.java.com.model.Exceptions.InsertException;
import app.java.com.model.Exceptions.SelectException;
import app.java.com.model.database.api.SelectCommand;
import app.java.com.presenter.interfaces.ResolveConflictResultInterface;

public class ProcessDuplicateRowsUseCase implements UseCase {

	private ResolveConflictResultInterface resolveConflictResultInterface;
	private List<InsertException> exceptions;

	public ProcessDuplicateRowsUseCase(
			ResolveConflictResultInterface resolveConflictResultInterface,
			List<InsertException> exceptionList) {
		this.resolveConflictResultInterface = resolveConflictResultInterface;
		this.exceptions = exceptionList;
	}

	@Override
	public void run() {

		for (InsertException exception : exceptions) {

			if (!exception.getType().equals("DuplicateKeyException")) {
				continue;
			}

			// otherwise duplicate exception
			String formattedTable = exception.getTable().replaceAll("`", "");
			List<List<String>> result = getAllDateForTable(formattedTable);
			List<String> primaryColumns = null;

			try {
				primaryColumns = new SelectCommand(formattedTable).getPrimaryKeyColumn();
			} catch (SelectException e) {
				continue; // move to next exception
			}

			List<Integer> primaryKeyColumnIndex = findPrimaryColumnIndex(primaryColumns, formattedTable);

			int rowNum = findDuplicateRow(result, primaryKeyColumnIndex, exception.getErrorValues());


			boolean isSame = false;

			if(rowNum != -1) {
                isSame = compareRows(result.get(rowNum), exception.getErrorValues());
            }

			if (isSame) {
				exception.setFixed(true);
			}

		}

		// done the usecase - pass info to the presenter
		resolveConflictResultInterface.onSuccessProcessingDuplicateRows(exceptions);
	}

	private boolean compareRows(List<String> originalRow, List<String> duplicateRow) {
		return originalRow.subList(1, originalRow.size() - 1).equals(duplicateRow.subList(1, duplicateRow.size() - 1));
	}

	private int findDuplicateRow(List<List<String>> dataResult, List<Integer> primaryKeysIndex,
			List<String> duplicatedVals) {

		if(dataResult == null || primaryKeysIndex == null || duplicatedVals == null) {
			return -1;
		}

		for (int x = 0; x < dataResult.size(); x++) {

			for (int columnIndex = 0; columnIndex < primaryKeysIndex.size(); columnIndex++) {

				String originalRowVal = dataResult.get(x).get(primaryKeysIndex.get(columnIndex));
				String duplicateRowVal = duplicatedVals.get(primaryKeysIndex.get(columnIndex));

				if (originalRowVal.equals(duplicateRowVal)) {
					return x;
				}
			}
		}

		return -1;
	}

	public List<List<String>> getAllDateForTable(String table) {

		SelectCommand command = new SelectCommand(table);
		List<List<String>> result = null;

		try {
			result = command.selectHandle();
		} catch (SelectException e) {
		    // nothing needed
            return null;
		}

		return result;
	}

	public List<Integer> findPrimaryColumnIndex(List<String> primaryKeyColumn, String table) {


		List<String> columns = null;
		List<Integer> result = new ArrayList<>();

		try {
			columns = new SelectCommand(table).getColumns();
		} catch (SelectException e) {
			return null;
		}


		int start = 0;

		if (columns != null) {
			for (int x = 0; x < columns.size(); x++) {
				if (start < primaryKeyColumn.size()
						&& columns.get(x).equals(primaryKeyColumn.get(start))) {
					result.add(x);
					start++;
				}
			}
		}

		return result;
	}
}
