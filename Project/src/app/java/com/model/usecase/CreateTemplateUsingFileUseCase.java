package app.java.com.model.usecase;

import java.util.ArrayList;
import java.util.List;

import app.java.com.model.Exceptions.DuplicateKeyException;
import app.java.com.model.Exceptions.InsertException;
import app.java.com.model.Exceptions.SelectException;
import app.java.com.model.database.api.Command;
import app.java.com.model.database.api.CreateCommand;
import app.java.com.model.database.api.InsertCommand;
import app.java.com.model.database.api.SelectCommand;
import app.java.com.model.entities.account.TeqAccount;
import app.java.com.model.entities.template.BaseTemplate;
import app.java.com.model.utilities.templateFile.TemplateFileCsvImpl;
import app.java.com.model.utilities.templateFile.TemplateFileExcelImpl;
import app.java.com.model.utilities.templateFile.TemplateFileInterface;
import app.java.com.presenter.interfaces.CreateTemplateResultInterface;

public class CreateTemplateUsingFileUseCase implements UseCase {


	private String filePath;
	private CreateTemplateResultInterface resultInterface;
	private TeqAccount account;

	private boolean lockSheetName = false;
	private String sheetName;

	private boolean lockColumnName = false;
	private List<String> columnNamesSelected;

	@SuppressWarnings("unused")
	private final String defaultAttr = "`id` int not null auto_increment";
	private final String clientDataFormIdCol = "`clientDataFormId` int not null";
	private final String clientDataFormIdFkName = "ClientDataFormIdFk";
	private final String clientDataFormIdFkCons = "constraint ";
	private final String clientDateFormIdFKRef = " foreign key (`clientDataFormId`) "
			+ "references `ClientDataForm`(`clientDataFormId`)";

	// VariableName table
	private final String variableNameTable = "VariableName";
	private final String variableNameCol = "variableName";
	private final String realNameCol = "realName";
	private final String templateNameCol = "templateName";

	// Template table
	private final String templateTable = "Template";
	private final String tableNameCol = "tableName";
	private final String teqStaffIdCol = "teqStaffId";

	// User table
	private final String userTable = "User";
	private final String accountIdCol = "accountId";
	private final String userIdCol = "userId";

	public CreateTemplateUsingFileUseCase(CreateTemplateResultInterface resultInterface,
			String filePath, TeqAccount account) {
		this.resultInterface = resultInterface;
		this.filePath = filePath;
		this.account = account;
	}


	@Override
	public void run() {
		BaseTemplate template = getTemplate(filePath);
		if (template == null) {
			return;
		}

		// get the fields needed to create specific table for a template
		String tableName = template.getTableName();
		List<String> columnIds = template.getColumnIds();
		List<String> requiredIds = template.getRequiredIds();
		List<String> columnNames = template.getColumnNames();
		List<String> requiredColumnNames = template.getRequiredColumnNames();
		String templateName = template.getTemplateName();
		boolean selected = waitClientToChoosePks(requiredColumnNames);
		if (!selected) {
			return;
		}
		List<String> primaryKeys = getPrimaryKeys(columnIds, columnNames, columnNamesSelected);
		try {
			createTemplateTale(tableName, columnIds, requiredIds, primaryKeys);
			// store all the columnIds and columnNames to VariableName table
			insertVariableNames(columnIds, columnNames, templateName);

			// insert to template table
			int staffId = getUserId(account);
			insertTemplate(templateName, tableName, staffId);
			resultInterface.onSuccessCreateTemplate();
		} catch (Exception e) {
			resultInterface.onErrorCreateTemplate(e.getMessage());
		}


	}

	private List<String> getPrimaryKeys(List<String> columnIds, List<String> coulumnNames,
			List<String> columnNamesSelected) {
		List<String> primaryKeys = new ArrayList<>();
		for (String selectedColumnName : columnNamesSelected) {
			int index = coulumnNames.indexOf(selectedColumnName);
			primaryKeys.add(columnIds.get(index));
		}
		return primaryKeys;
	}


	public boolean waitClientToChoosePks(List<String> requiredColumnNames) {
		resultInterface.fetchPKs(requiredColumnNames);
		while ((!lockColumnName) && columnNamesSelected == null) {

		}
		boolean selected = !(columnNamesSelected == null);
		return selected;
	}

	public void sheetSelected(String sheetName) {
		this.sheetName = sheetName;
		this.lockSheetName = true;
	}

	public void PKselected(List<String> columnNamesSelected) {
		this.columnNamesSelected = columnNamesSelected;
		this.lockColumnName = true;
	}

	private BaseTemplate getTemplate(String filePath) {
		String formulatedFileName = filePath.replace("\\", "\\\\");
		TemplateFileInterface file = null;
		if (formulatedFileName.substring(formulatedFileName.length() - 4).equals("xlsx")) {
			// get the sheetNames ask client to choose
			file = new TemplateFileExcelImpl(filePath);
			List<String> sheetNames = ((TemplateFileExcelImpl) file).getSheetNames();
			resultInterface.fetchSheetNames(sheetNames);

			// wait client to choose sheet
			while ((!lockSheetName) && sheetName == null) {
			}

			// cancel the dialog, not selected sheet
			if (lockSheetName && sheetName == null) {
				return null;
			}

			// get here only when selected a sheet
			((TemplateFileExcelImpl) file).setSheetName(sheetName);
		} else if (formulatedFileName.substring(formulatedFileName.length() - 3).equals("csv")) {
			file = new TemplateFileCsvImpl(formulatedFileName);
		}
		BaseTemplate template = (BaseTemplate) file.getFileAsTemplate();
		return template;
	}

	private List<String> addConstraintsToIds(List<String> ids, List<String> requiredIds) {
		List<String> result = new ArrayList<String>();
		for (String id : ids) {
			if (requiredIds.contains(id)) {
				result.add(id.concat(" varchar(255) not null"));
			} else {
				result.add(id.concat(" varchar(255)"));
			}
		}
		return result;
	}

	/*
	 * using all the required ids and defaultAttr as primary key
	 */
	private String formulatePK(List<String> requiredIds) {
		int len = requiredIds.toString().length();
		String res = "primary key (" + requiredIds.toString().substring(1, len - 1) + ")";
		return res;
	}

	/*
	 * insert variableName mapping to variableName table
	 */
	private boolean insertVariableName(String columnId, String columnName, String templateName)
			throws DuplicateKeyException, InsertException {
		List<String> data = new ArrayList<String>();
		data.add(columnId);
		data.add(columnName);
		data.add(templateName);
		List<String> attrs = new ArrayList<String>();
		attrs.add(variableNameCol);
		attrs.add(realNameCol);
		attrs.add(templateNameCol);
		InsertCommand insertVariable = new InsertCommand(variableNameTable, attrs, data);
		return insertVariable.handle();
	}

	private void insertVariableNames(List<String> columnIds, List<String> columnNames,
			String templateName) throws InsertException {
		int len = columnIds.size();
		for (int i = 0; i < len; i++) {
			try {
				insertVariableName(columnIds.get(i), columnNames.get(i), templateName);
			} catch (DuplicateKeyException e) {
				continue;
			}
		}

	}

	private void insertTemplate(String templateName, String tableName, int staffId)
			throws Exception {
		// store the template's real name and table name in the database
		List<String> attrNames = new ArrayList<String>();
		attrNames.add(templateNameCol);
		attrNames.add(tableNameCol);
		attrNames.add(teqStaffIdCol);

		List<String> vals = new ArrayList<String>();
		vals.add(templateName);
		vals.add(tableName);
		vals.add(Integer.toString(staffId));
		Command insertTemplate = new InsertCommand(templateTable, attrNames, vals);
		insertTemplate.handle();
	}

	private boolean createTemplateTale(String tableName, List<String> columnIds,
			List<String> requiredIds, List<String> primaryKeys) throws Exception {
		List<String> idsWithConstraints = addConstraintsToIds(columnIds, requiredIds);
		// add clienteDataFormId foreign key
		idsWithConstraints.add(0, constructDataFormFk(tableName));
		idsWithConstraints.add(0, this.clientDataFormIdCol);

		// add primary key constraint at the end
		idsWithConstraints.add(formulatePK(primaryKeys));

		Command createTemplate = new CreateCommand(tableName, idsWithConstraints);
		return createTemplate.handle();
	}

	private int getUserId(TeqAccount account) throws SelectException {
		List<String> tar = new ArrayList<>();
		tar.add(userIdCol);
		List<String> cons = new ArrayList<>();
		cons.add(accountIdCol + " = " + account.getAccountId());
		SelectCommand findUserId = new SelectCommand(tar, userTable, cons);
		List<String> userIdStr = findUserId.selectHandleSingle();
		return Integer.parseInt(userIdStr.get(0));
	}

	private String constructDataFormFk(String tableName) {
		String constraintName = "`"
				+ tableName.substring(1, tableName.length() - 1)
				+ this.clientDataFormIdFkName
				+ "`";
		String result = clientDataFormIdFkCons + constraintName + clientDateFormIdFKRef;
		return result;
	}

}
