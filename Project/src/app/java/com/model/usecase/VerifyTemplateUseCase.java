package app.java.com.model.usecase;

import java.util.ArrayList;
import java.util.List;

import app.java.com.model.Exceptions.SelectException;
import app.java.com.model.database.api.SelectCommand;
import app.java.com.model.utilities.templateFile.TemplateFileInterface;
import app.java.com.presenter.interfaces.VerifyTemplateResultInterface;

public class VerifyTemplateUseCase implements UseCase {

	private VerifyTemplateResultInterface resultInterface;
	private TemplateFileInterface file;
	private String templateName;
	private final String templateTable = "`Template`";
	private final String templateCol = "templateName";
	private final String tableNameCol = "tableName";

	public VerifyTemplateUseCase(VerifyTemplateResultInterface resultInterface,
			TemplateFileInterface fileInterface, String templateName) {
		this.resultInterface = resultInterface;
		this.file = fileInterface;
		this.templateName = templateName;
	}

	@Override
	public void run() {
		List<String> columnList = new ArrayList<>();
		columnList.add(tableNameCol);

		List<String> constraints = new ArrayList<>();
		constraints.add(templateCol + "= \'" + templateName + "\'");

		// Get the table name based on template name given
		SelectCommand tableNameCommand = new SelectCommand(columnList, templateTable, constraints);
		List<List<String>> resultSet = null;
		try {
			resultSet = tableNameCommand.selectHandle();
		} catch (SelectException e) {
			this.resultInterface.onTemplateSelectedCompatible(false, file);
			return;
		}

		SelectCommand command = new SelectCommand(resultSet.get(0).get(0));

		// Get the number of columns for the selected Template
		List<String> selectedTemplateColumns = null;

		try {
			selectedTemplateColumns = command.getColumnIds();
		} catch (SelectException e) {
			this.resultInterface.onTemplateSelectedCompatible(false, file);
			return;
		}

		List<String> selectedFileColumns = file.getColumnIds();

		int adjustedTemplateColumns = selectedTemplateColumns.size() - 1;
		int templateColumnIndex = 1;

		if (adjustedTemplateColumns != selectedFileColumns.size()) {
			this.resultInterface.onTemplateSelectedCompatible(false, file);
			return;
		}

		for (int columnIdIndex = 0; columnIdIndex < selectedFileColumns.size(); columnIdIndex++) {
			if (!selectedFileColumns.get(columnIdIndex)
					.equals(selectedTemplateColumns.get(templateColumnIndex))) {
				this.resultInterface.onTemplateSelectedCompatible(false, file);
				return;
			}

			templateColumnIndex++;
		}

		// If manage to get here, then send template valid result
		this.resultInterface.onTemplateSelectedCompatible(true, file);
	}
}
