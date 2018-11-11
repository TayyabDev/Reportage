package app.java.com.model.usecase;

import java.util.*;

import app.java.com.model.Exceptions.SelectException;
import app.java.com.model.database.api.SelectCommand;
import app.java.com.presenter.interfaces.CustomReportResultInterface;

public class GenerateCustomReportUseCase extends UseCase{

    private HashMap<String, List<String>> templateRealNameMap;
    private Calendar begin;
    private Calendar end;
    CustomReportResultInterface resultInterface;

    public GenerateCustomReportUseCase(CustomReportResultInterface resultInterface, HashMap<String, List<String>> templateRealNameMap, Calendar begin, Calendar end) {
        this.resultInterface = resultInterface;
        this.templateRealNameMap = templateRealNameMap;
        this.begin = begin;
        this.end = end;


    }
    @Override
    public void run() {
        // get the tableName and variableName used in database
        // based on the given templateName and realName given from ui
        try {
            HashMap<String, List<String>> tableVariableMap = getTableVariableMap(templateRealNameMap);
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
    public static String getTableName(String templateName) throws SelectException {
        List<String> getTableNameCommTar = new ArrayList<>();
        getTableNameCommTar.add("tableName");
        List<String> getTableNameCommCons = new ArrayList<>();
        getTableNameCommCons.add("templateName = \'" + templateName + "\'");
        SelectCommand getTableNameComm = new SelectCommand(getTableNameCommTar, "Template", getTableNameCommCons);
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
    public static List<String> getVarNames(String templateName, List<String> realNames) throws SelectException {
        // get the corresponding varNames
        List<String> varNames = new ArrayList<>();

        List<String> getVariableCommTar = new ArrayList<String>();
        getVariableCommTar.add("variableName");

        for (String realName : realNames) {
            List<String> getVarNameCommCons = new ArrayList<>();
            getVarNameCommCons.add("templateName = \'" + templateName + "\'");
            getVarNameCommCons.add("realName = \'" + realName + "\'");
            SelectCommand getVarNameComm = new SelectCommand(getVariableCommTar, "VariableName", getVarNameCommCons);
            List<String>  varNameList = getVarNameComm.selectHandleSingle();
            if (varNameList.size() != 1) {
                // should never goto here, unless power off etc during the the program is running
                throw new SelectException("something unexpected happened, please try agian later.");
            }
            String varName = varNameList.get(0);
            varNames.add(varName.substring(1, varName.length()-1));
        }
        return varNames;
    }

    /*
     * get the {tableName:[id]} used in the database given the {templateName: [realName]}
     */
    public static HashMap<String, List<String>> getTableVariableMap(HashMap<String, List<String>> templateRealNameMap) throws SelectException {

        HashMap<String, List<String>> tableVariableMap = new HashMap<>();

        List<String> getVariableCommTar = new ArrayList<String>();
        getVariableCommTar.add("variableName");

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
        String year = String.format("%04d", cal.get(Calendar.YEAR));
        String month = String.format("%02d", cal.get(Calendar.MONTH));
        return "date(\'"+ year + "-" + month + "-" + "01\')";
    }

    /*
     * get the ClientDataFormId given the time range
     */
    public static List<String> getDataFormIds(Calendar begin, Calendar end) throws SelectException {
        // select id from ClientDataForm where year = year and month = month
        List<String> tar = new ArrayList<>();
        tar.add("clientDataFormId");
        List<String> constraints = new ArrayList<>();

        String beginStr = formulateCalendar(begin);
        String endStr = formulateCalendar(end);
        constraints.add("concat(year, '-', month, '-', '01') between "+ beginStr+ " and "+ endStr);
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
    public static HashMap<String, List<List<String>>> getData(HashMap<String, List<String>> tableVariableMap, List<String> dataFormIds) throws SelectException {
        // format the constraint: clientDataFormId in {dataFormIds}
        String dataFormIdsSet = dataFormIds.toString().replace('[', '(').replace(']', ')');
        List<String> getDataCommCons = new ArrayList<>();
        getDataCommCons.add("clientDataFormId in "+ dataFormIdsSet);

        HashMap<String, List<List<String>>> results = new HashMap<>();
        // get tableName
        for (String table : tableVariableMap.keySet()) {
            // select varName from tableName where clientDataFormId in dataFormIds
            List<String> varNames = tableVariableMap.get(table);
            SelectCommand getDataComm = new SelectCommand(varNames, table, getDataCommCons);
            List<List<String>> dataCol = getDataComm.selectHandle();
            results.put(table, dataCol);
        }
        return results;

    }

    	public static void main(String[] argv) throws SelectException {
		List<String> cp = new ArrayList<>();
		List<String> cc = new ArrayList<>();
		cc.add("Referred By");
		cc.add("Child 5: Type of Care");

		cp.add("City");

		HashMap<String, List<String>> lis = new HashMap<>();
		lis.put("Community Connections", cc);
		lis.put("Client Profile", cp);
		HashMap<String, List<String>> res = getTableVariableMap(lis);
            System.out.println(res);

		Calendar  begin = new GregorianCalendar(2010, 9, 26);
		Calendar  end = new GregorianCalendar(2018,11,10);
		String x = "123";
		System.out.println(x.substring(1, x.length()-1));

		List<String> timeRange = getDataFormIds(begin, end);
		System.out.println("no error");
		System.out.println(getData(res, timeRange));
	}
}