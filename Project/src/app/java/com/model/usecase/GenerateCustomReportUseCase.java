package app.java.com.model.usecase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import app.java.com.model.Exceptions.SelectException;
import app.java.com.model.database.api.SelectCommand;
import app.java.com.presenter.interfaces.CustomReportResultInterface;

public class GenerateCustomReportUseCase implements UseCase {

	private static HashMap<String, List<String>> templateRealNameMap;
	private Calendar begin;
	private Calendar end;
	CustomReportResultInterface resultInterface;

	// in VariableName Table, there are 3 columns
	// variableName(name in database), realName(name in file), templateName(name in file)
	private final String variableNameTable = "VariableName";
	private final String variableNameCol = "variableName";
	private final String realNameCol = "realName";
	private final String templateNameCol = "templateName";

	// in Template table
	// templateName(name in file), tableName(name in database)
	private final String templateTable = "Template";
	private final String tableNameCol = "tableName";

	// in ClientDataForm table
	// year, month
	private final String clientDataFormTable = "ClientDataForm";
	private final String yearCol = "year";
	private final String monthCol = "month";

	@SuppressWarnings("static-access")
	public GenerateCustomReportUseCase(CustomReportResultInterface resultInterface,
			HashMap<String, List<String>> templateRealNameMap, Calendar begin, Calendar end) {
		this.resultInterface = resultInterface;
		this.templateRealNameMap = templateRealNameMap;
		System.out.println(templateRealNameMap);
		this.begin = begin;
		this.end = end;


	}

	@Override
	public void run() {
		// get the tableName and variableName used in database
		// based on the given templateName and realName given from ui
		try {
			HashMap<String, List<String>> tableVariableMap =
					getTableVariableMap(templateRealNameMap);
			List<String> dataFormIds = getDataFormIds(begin, end);
			HashMap<String, List<List<String>>> res = getData(tableVariableMap, dataFormIds);
			resultInterface.sendReport(res);
		} catch (SelectException e) {
			e.printStackTrace();
			resultInterface.onErrorCreatingReport();
		}
		//

	}

	/*
	 * find the corresponding tableName given templateName
	 */
	public String getTableName(String templateName) throws SelectException {
		List<String> getTableNameCommTar = new ArrayList<>();
		getTableNameCommTar.add(tableNameCol);
		List<String> getTableNameCommCons = new ArrayList<>();
		getTableNameCommCons.add(templateNameCol + " = \'" + templateName + "\'");
		SelectCommand getTableNameComm =
				new SelectCommand(getTableNameCommTar, templateTable, getTableNameCommCons);
		List<String> tableNameList = getTableNameComm.selectHandleSingle();

		if (tableNameList.size() == 1) {
			return tableNameList.get(0);
		} else {
			// should never goto here, unless power off etc during the the program is running
			throw new SelectException("something unexpected happened, please try agian later.");
		}
	}

	/*
	 * get the corresponding varNames in a list given the template Name and a list of realNames
	 */
	public List<String> getVarNames(String templateName, List<String> realNames)
			throws SelectException {
		// get the corresponding varNames
		List<String> varNames = new ArrayList<>();

		List<String> getVariableCommTar = new ArrayList<String>();
		getVariableCommTar.add(variableNameCol);

		for (String realName : realNames) {
			List<String> getVarNameCommCons = new ArrayList<>();
			getVarNameCommCons.add(templateNameCol + " = \'" + templateName + "\'");
			getVarNameCommCons.add(realNameCol + " = \'" + realName + "\'");
			SelectCommand getVarNameComm =
					new SelectCommand(getVariableCommTar, variableNameTable, getVarNameCommCons);
			List<String> varNameList = getVarNameComm.selectHandleSingle();
			if (varNameList.size() != 1) {
				// should never goto here, unless power off etc during the the program is running
				throw new SelectException("something unexpected happened, please try agian later.");
			}
			String varName = varNameList.get(0);
			varNames.add(varName.substring(1, varName.length() - 1));
		}
		return varNames;
	}

	/*
	 * get the {tableName:[id]} used in the database given the {templateName: [realName]}
	 */
	public HashMap<String, List<String>> getTableVariableMap(
			HashMap<String, List<String>> templateRealNameMap) throws SelectException {

		HashMap<String, List<String>> tableVariableMap = new HashMap<>();

		List<String> getVariableCommTar = new ArrayList<String>();
		getVariableCommTar.add(variableNameCol);

		for (String templateName : templateRealNameMap.keySet()) {
			// get the corresponding tableName
			String tableName = getTableName(templateName);

			// get the corresponding varNames

			List<String> realNames = templateRealNameMap.get(templateName);
			List<String> varNames = getVarNames(templateName, realNames);

			// put the table name and corresponding varNames to the HashMap
			tableVariableMap.put(tableName, varNames);
		}
		return tableVariableMap;
	}

	/*
	 * return the formulated date 2018-01-01 given a calendar ignore the date in the calendar
	 */
	private static String formulateCalendar(Calendar cal) {
		String year, month;
		if (cal == null) {
			return null;
		} else {
			year = String.format("%04d", cal.get(Calendar.YEAR));
			month = String.format("%02d", cal.get(Calendar.MONTH));
		}
		return "date(\'" + year + "-" + month + "-" + "01\')";
	}

	/*
	 * get the ClientDataFormId given the time range
	 */
	public List<String> getDataFormIds(Calendar begin, Calendar end) throws SelectException {
		// select id from ClientDataForm where year = year and month = month
		List<String> tar = new ArrayList<>();
		tar.add("clientDataFormId");
		List<String> constraints = new ArrayList<>();

		String beginStr = formulateCalendar(begin);
		String endStr = formulateCalendar(end);

		if (beginStr == null || endStr == null) {
			return null;
		}
		constraints.add("concat("
				+ yearCol
				+ ", '-', "
				+ monthCol
				+ ", '-', '01') between "
				+ beginStr
				+ " and "
				+ endStr);
		SelectCommand getDataFormIdsComm = new SelectCommand(tar, clientDataFormTable, constraints);
		List<String> dataFormIds = getDataFormIdsComm.selectHandleSingle();
		return dataFormIds;

	}

	/*
	 * select * from tableName where clientDataFormId in {clientDataFormIds}
	 *
	 * @param templateVarNames [[tableName, id]]
	 */
	public static HashMap<String, List<List<String>>> getData(
			HashMap<String, List<String>> tableVariableMap, List<String> dataFormIds)
			throws SelectException {
		// format the constraint: clientDataFormId in {dataFormIds}
		List<String> getDataCommCons = null;
		if (dataFormIds != null) {
			String dataFormIdsSet = dataFormIds.toString().replace('[', '(').replace(']', ')');
			getDataCommCons = new ArrayList<>();
			getDataCommCons.add("clientDataFormId in " + dataFormIdsSet);
		}
		HashMap<String, List<List<String>>> results = new HashMap<>();
		// get tableName

		ArrayList<String> sortedTemplateRealMap =
				new ArrayList<String>(templateRealNameMap.keySet());
		Collections.sort(sortedTemplateRealMap);
		ArrayList<String> sortedTableVarMap = new ArrayList<String>(tableVariableMap.keySet());
		Collections.sort(sortedTableVarMap);

		int tableIndex = 0;
		for (String table : sortedTableVarMap) {
			// select varName from tableName where clientDataFormId in dataFormIds
			List<String> varNames = tableVariableMap.get(table);
			results.put(table, new ArrayList<>(
					Arrays.asList(templateRealNameMap.get(sortedTemplateRealMap.get(tableIndex)))));

			SelectCommand getDataComm;
			if (getDataCommCons != null) {
				getDataComm = new SelectCommand(varNames, table, getDataCommCons);
			} else {
				getDataComm = new SelectCommand(varNames, table);
			}
			List<List<String>> dataCol;
			try {
				dataCol = getDataComm.selectHandle();
				results.get(table).addAll(dataCol);
			} catch (Exception e) {

			}

			tableIndex += 1;
		}
		return results;

	}
}
