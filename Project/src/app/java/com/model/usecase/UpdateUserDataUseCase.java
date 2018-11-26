package app.java.com.model.usecase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import app.java.com.model.Exceptions.SelectException;
import app.java.com.model.database.api.SelectCommand;
import app.java.com.model.database.api.UpdateCommand;
import app.java.com.model.entities.DataChanges;
import app.java.com.presenter.interfaces.UpdateUserDataResultInterface;

public class UpdateUserDataUseCase implements UseCase {

	private UpdateUserDataResultInterface resultInterface;
	private List<List<String>> originalData;
	private List<List<String>> newData;
	private List<DataChanges> changesList;
	private String templateName;

	public UpdateUserDataUseCase(UpdateUserDataResultInterface resultInterface, String tableName,
			List<List<String>> originalData, List<List<String>> newData) {
		this.resultInterface = resultInterface;
		this.originalData = originalData;
		this.newData = newData;
		this.changesList = new ArrayList<>();
		this.templateName = tableName;
	}

	public UpdateUserDataUseCase(UpdateUserDataResultInterface resultInterface, String tableName,
			List<DataChanges> changes) {
		this.resultInterface = resultInterface;
		this.changesList = changes;
		this.templateName = tableName;
	}

	public void runCompare() {
		for (int i = 0; i < originalData.size(); i++) {
			for (int j = 0; j < originalData.get(i).size(); j++) {

				String originalValue = originalData.get(i).get(j);
				String newValue = newData.get(i).get(j);

				if (!originalValue.equals(newValue)) {
					DataChanges dataChanges =
							new DataChanges(templateName, i, j, originalValue, newValue);
					changesList.add(dataChanges);
				}
			}
		}
		resultInterface.onShowDataChanges(changesList);
	}

	@Override
	public void run() {
		SelectCommand selectTableNameCommand = new SelectCommand(Arrays.asList("tableName"),
				"Template", Arrays.asList("templateName = '" + templateName + "'"));
		String tableName = null;
		try {
			tableName = selectTableNameCommand.selectHandleSingle().get(0).replaceAll("`", "");
		} catch (SelectException e2) {
			resultInterface.onErrorUpdate("Cannot select table name from template name");
			e2.printStackTrace();
		}
		SelectCommand selectCommand = new SelectCommand(tableName);
		List<String> columnIds = null;
		try {
			columnIds = selectCommand.getColumns();
		} catch (SelectException e1) {
			resultInterface.onErrorUpdate("Unable to select columns");
			e1.printStackTrace();
		}

		int changeIndex = 0;
		while (changeIndex < changesList.size()) {
			int rowIndex = changesList.get(changeIndex).getRow();
			List<String> targets = new ArrayList<String>();
			List<String> constraints = new ArrayList<String>();
			List<String> values = new ArrayList<String>();
			List<String> primaryKeyValues = null;
			List<String> primaryKeyNames = null;
			try {
				primaryKeyValues = selectCommand.getValuesOfRowAtPrimaryKeys(rowIndex);
				primaryKeyNames = selectCommand.getPrimaryKeyColumn();
			} catch (SelectException e) {
				e.printStackTrace();
			}
			for (int i = 0; i < primaryKeyValues.size(); i++) {
				constraints.add(primaryKeyNames.get(i) + " = '" + primaryKeyValues.get(i) + "'");
			}
			String col = columnIds.get(changesList.get(changeIndex).getColumn());
			targets.add(col);
			values.add("'" + changesList.get(changeIndex).getNewValue() + "'");
			changeIndex++;
			try {
				UpdateCommand updateCommand =
						new UpdateCommand(tableName, targets, values, constraints);
				if (updateCommand.handle()) {
					resultInterface.onSuccessUpdate("Successfully updated table values");
				} else {
					resultInterface.onErrorUpdate("Unsuccessfully updated table values");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
