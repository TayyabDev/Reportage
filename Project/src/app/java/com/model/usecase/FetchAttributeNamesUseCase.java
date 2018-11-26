package app.java.com.model.usecase;

import java.util.ArrayList;
import java.util.List;

import app.java.com.model.Exceptions.SelectException;
import app.java.com.model.database.api.SelectCommand;
import app.java.com.presenter.interfaces.FetchAttributeNamesResultInterface;

public class FetchAttributeNamesUseCase implements UseCase {

	private FetchAttributeNamesResultInterface resultInterface;
	// in VariableName Table, there are 3 columns
	// variableName(name in database), realName(name in file), templateName(name in file)
	private final String variableNameTable = "VariableName";
	private final String variableNameCol = "variableName";
	private final String realName = "realName";
	private final String templateNameCol = "templateName";

	// in Template table
	// templateName(name in file), tableName(name in database)
	private final String templateTable = "Template";
	private final String tableNameCol = "tableName";

	public FetchAttributeNamesUseCase(FetchAttributeNamesResultInterface resultInterface) {
		this.resultInterface = resultInterface;
	}


	@Override
	public void run() {
		try {
			List<String> attrs = fetchAttributeNames();
			resultInterface.onSuccessFetchingAttributes(attrs);
		} catch (SelectException e) {
			resultInterface.onErrorFetchingAttributes(e.getMessage());
		}

	}


	/*
	 * get the list of template table name in the database
	 */
	public List<String> fetchTemplateTableName() throws SelectException {
		List<String> target = new ArrayList<String>();

		target.add(tableNameCol);
		SelectCommand fetchTemplateTableNameComm = new SelectCommand(target, templateTable);
		List<String> templateTableNames = fetchTemplateTableNameComm.selectHandleSingle();
		return templateTableNames;
	}

	/*
	 * get the attributes names for each of the template table in the database
	 */
	public List<String> fetchAttributeNames(List<String> tableNames) throws SelectException {
		List<String> allAttributeNames = new ArrayList<String>();
		for (String tableName : tableNames) {
			SelectCommand getAttributeComm = new SelectCommand(tableName);
			List<String> attrNameForOneTable = getAttributeComm.getColumnIds();
			// remove the first three columns from each table
			// (id, clientDataFormId, processing_detail)
			for (int i = 0; i < 3; i++) {
				attrNameForOneTable.remove(0);
			}
			allAttributeNames.addAll(attrNameForOneTable);
		}
		List<String> formulatedRealNames = new ArrayList<>();
		// need to get the real name for the user not the database ids
		for (String id : allAttributeNames) {
			List<String> target = new ArrayList<>();
			target.add(realName);
			List<String> constraint = new ArrayList<>();
			constraint.add("`" + variableNameCol + "` = \'`" + id + "`\'");
			SelectCommand getRealNameComm =
					new SelectCommand(target, variableNameTable, constraint);
			List<String> realNames = getRealNameComm.selectHandleSingle();
			formulatedRealNames.addAll(realNames);
		}
		return formulatedRealNames;
	}

	public List<String> fetchAttributeNames() throws SelectException {
		List<String> target = new ArrayList<>();
		target.add(templateNameCol);
		target.add(realName);
		String orderVariableNameTale = variableNameTable + " order by variableNameId";
		SelectCommand fetchAttrComm = new SelectCommand(target, orderVariableNameTale);
		List<List<String>> res = fetchAttrComm.selectHandle();
		List<String> formulatedRes = new ArrayList<String>();
		for (List<String> row : res) {
			if (!row.isEmpty()) {
				String templateName = row.get(0);
				String attrName = row.get(1);
				formulatedRes.add(templateName + " -- " + attrName);
			}
		}
		return formulatedRes;
	}


	/*
	 * public static HashMap<String, List<String>> fetchAttributeNames() throws SelectException {
	 * List<String> target = new ArrayList<>(); target.add("templateName"); target.add("realName");
	 * SelectCommand fetchAttrComm = new SelectCommand(target, "VariableName"); List<List<String>>
	 * res = fetchAttrComm.selectHandle();
	 * 
	 * HashMap<String, List<String>> formulatedRes = new HashMap<>(); for (List<String> row : res) {
	 * if (!row.isEmpty()) { String templateName = row.get(0); String attrName = row.get(1);
	 * if(formulatedRes.containsKey(templateName)){ formulatedRes.get(templateName).add(attrName); }
	 * else { formulatedRes.put(templateName, new ArrayList<String>(Arrays.asList(attrName))); } } }
	 * return formulatedRes; }
	 */
}
