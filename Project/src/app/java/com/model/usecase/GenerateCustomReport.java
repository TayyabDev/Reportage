package app.java.com.model.usecase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import app.java.com.model.Exceptions.SelectException;
import app.java.com.model.database.api.SelectCommand;

public class GenerateCustomReport extends UseCase{

	private List<String> formatedText;
	private String timeRange;
	
	public GenerateCustomReport(List<String> formatedText) {
		this.formatedText = formatedText;
		
		
	}
	@Override
	public void run() {
		// get the tableName and variableName used in database
		// based on the given templateName and realName used in ui
		try {
			List<List<String>> tableVar = getTableNameVarNames(formatedText);
			
		} catch (SelectException e) {
			//error
		}
		// 
		
	}

	
	/*
	 * get the [[tableName id]] used in the database given the [templateName -- realName]
	 */
	public static List<List<String>> getTableNameVarNames(List<String> formatedText) throws SelectException {
		List<List<String>> TableNameVarNames = new ArrayList<>();
		List<String> getVarNameCommTar = new ArrayList<String>();
		getVarNameCommTar.add("variableName");
		String[] splitedRow;
		String templateName;
		String realName;
		for (String row : formatedText) {
			// get the templateName and realName out
			splitedRow = row.split(" -- ");
			templateName = splitedRow[0];
			realName = splitedRow[1];
			// get the corresponding tableName and id, store them
			List<String> getVarNameCommCons = new ArrayList<>();
			getVarNameCommCons.add("realName = \'" + realName + "\'");
			getVarNameCommCons.add("templateName = \'" + templateName + "\'");
			SelectCommand getVarNameComm = new SelectCommand(getVarNameCommTar, "VariableName", getVarNameCommCons);
			List<String>  varName = getVarNameComm.selectHandleSingle();
			if (!varName.isEmpty()) {
				List<String> templateValName = new ArrayList<>();
				
				List<String> getTableNameCommTar = new ArrayList<>();
				getTableNameCommTar.add("tableName");
				List<String> getTableNameCommCons = new ArrayList<>();
				getTableNameCommCons.add("templateName = \'" + templateName + "\'");
				SelectCommand getTableNameComm = new SelectCommand(getTableNameCommTar, "Template", getTableNameCommCons);
				List<String> tableName = getTableNameComm.selectHandleSingle();
				templateValName.addAll(tableName);
				templateValName.add(varName.get(0));
				TableNameVarNames.add(templateValName);
			}
//			columnIds.add(varName);
		}
		return TableNameVarNames;
	}
	
	/*
	 * return thre fomulated data 2018-01-01
	 */
	private static String formuateDate(String year, String month) {
		return "\'"+ year + "-" + month + "-" + "01\'";
	}
	
	/*
	 * get the ClientDataFormId given the time range
	 */
	public static List<String> getDataFormIds(String beginYear, String beginMonth, String endYear, String endMonth) throws SelectException {
		// select id from ClientDataForm where year = year and month = month
		List<String> tar = new ArrayList<>();
		tar.add("clientDataFormId");
		List<String> constraints = new ArrayList<>();
		String begin = formuateDate(beginYear, beginMonth);
		String end = formuateDate(endYear, endMonth);
		constraints.add("concat(year, '-', month, '-', '01') between "+ begin+ " and "+ end);
		SelectCommand getDataFormIdsComm = new SelectCommand(tar, "ClientDataForm", constraints);
		List<String> dataFormIds = getDataFormIdsComm.selectHandleSingle();
		return dataFormIds;
		
	}
	
	/*
	 * select * 
	 * from tableName
	 * where clientDataFormId in {clientDataFormIds}
	 * 
	 * @param templateVarNames [[tableName, id]]
	 */
	public static HashMap<String, List<String>> getData(List<List<String>> tableVarNames, List<String> dataFormIds) throws SelectException {
		// format the constraint: [clientDataFormIds] into {clientDataFormIds}
		String dataFormIdsSet = dataFormIds.toString().replace('[', '{').replace(']', '}');
		List<String> getDataCommCons = new ArrayList<>();
		getDataCommCons.add("clientDataFormId in "+ dataFormIdsSet);
		
		HashMap<String, List<String>> results = new HashMap<String, List<String>>(); 
		// get tableName
		for (List<String> tableVarName : tableVarNames) {
			// select varName from tableName where clientDataFormId in dataFormIds
			String tableName = tableVarName.get(0);
			String varName = tableVarName.get(1);
			List<String> getDataCommTar = new ArrayList<>();
			getDataCommTar.add(varName);
			SelectCommand getDataComm = new SelectCommand(getDataCommTar, tableName, getDataCommCons);
			List<String> dataCol = getDataComm.selectHandleSingle();
			results.put(varName, dataCol);
		}
		return results;
		
	}
	
	public static void main(String[] argv) throws SelectException {
		List<String> lis = new ArrayList<>();
//		String x = "Community Connections -- City";
//		System.out.println(x.split(" -- ", 2)[0]);
//		System.out.println(x.split(" || ", 2)[1]);
		lis.add("Community Connections -- Referred By");
		lis.add("Client Profile -- City");
		
		List<List<String>> res = getTableNameVarNames(lis);
		System.out.println(res);
//		System.out.println(getDataFormIds("2018", "11", "2018", "11"));
		
	}
}
