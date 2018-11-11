package app.java.com.model.usecase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import app.java.com.model.Exceptions.SelectException;
import app.java.com.model.database.api.SelectCommand;
import app.java.com.presenter.interfaces.FetchAttributeNamesResultInterface;

public class FetchAttributeNamesUseCase extends UseCase{

	public FetchAttributeNamesResultInterface resultInterface;
	// in VariableName Table, there are 3 columns
	//variableName(name in database), realName(name in file), templateName(name in file)
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
	
	/*
	 *  fetch all the attributes names in the database, sent it to the view
	 */
	@Override
	public void run() {
		
		try {
			HashMap<String, List<String>> attributesNames = fetchAttributeNames();
			this.resultInterface.onSuccessFetchAttributeNames(attributesNames);
		} catch (SelectException e) {
			System.out.println(e.getMessage());
			this.resultInterface.onErrorFetchAttributeNames(e.getMessage());
		}
	}
	
	/*
	 * return a HashMap{templateName : [realName]} to show to the client
	 */
	public HashMap<String, List<String>> fetchAttributeNames() throws SelectException {
		List<String> target = new ArrayList<>();
		target.add(templateNameCol);
		target.add(realName);
		String orderedTable = variableNameTable + " order by variableNameId";
		SelectCommand fetchAttrComm = new SelectCommand(target, orderedTable);
		List<List<String>> res = fetchAttrComm.selectHandle();
		HashMap<String, List<String>> mapRes = new HashMap<>();
		for (List<String> row : res) {
			if (!row.isEmpty()) {
				String templateName = row.get(0);
				String attrName = row.get(1);
				if (mapRes.containsKey(templateName)) {
					mapRes.get(templateName).add(attrName);
				} else {
					List<String> newAttrList = new ArrayList<>();
					newAttrList.add(attrName);
					mapRes.put(templateName, newAttrList);
				}
			}
		}
		return mapRes;
	}

//
//	/*
//	 * get the list of template table name in the database
//	 */
//	public List<String> fetchTemplateTableName() throws SelectException {
//		List<String> target = new ArrayList<String>();
//		
//		target.add(tableNameCol);
//		SelectCommand fetchTemplateTableNameComm = new SelectCommand(target, templateTable);
//		List<String> templateTableNames = fetchTemplateTableNameComm.selectHandleSingle();
//		return templateTableNames;
//	}
//	
//	/*
//	 * get the attributes names for each of the template table in the database
//	 */
//	public List<String> fetchAttributeNames(List<String> tableNames) throws SelectException {
//		List<String> allAttributeNames = new ArrayList<String>();
//		for (String tableName : tableNames) {
//			SelectCommand getAttributeComm = new SelectCommand(tableName);
//			List<String> attrNameForOneTable = getAttributeComm.getColumnIds();
//			// remove the first three columns from each table
//			// (id, clientDataFormId, processing_detail)
//			for (int i = 0; i < 3; i++) {
//				attrNameForOneTable.remove(0);
//			}
//			allAttributeNames.addAll(attrNameForOneTable);
//		}
//		List<String> formulatedRealNames = new ArrayList<>();
//		// need to get the real name for the user not the database ids
//		for (String id : allAttributeNames) {
//			List<String> target = new ArrayList<>();
//			target.add(realName);
//			List<String> constraint = new ArrayList<>();
//			constraint.add("`" + variableNameCol +"` = \'`" + id+ "`\'");
//			SelectCommand getRealNameComm = new SelectCommand(target, variableNameTable, constraint);
//			List<String> realNames = getRealNameComm.selectHandleSingle();
//			formulatedRealNames.addAll(realNames);
//		}
//		return formulatedRealNames;
//	}

	
}
