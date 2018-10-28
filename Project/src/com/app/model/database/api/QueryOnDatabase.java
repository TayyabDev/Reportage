package app.java.com.model.database.api;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import app.java.com.model.Exceptions.AlterException;
import app.java.com.model.Exceptions.CreateException;
import app.java.com.model.Exceptions.DropException;
import app.java.com.model.Exceptions.InsertException;
import app.java.com.model.Exceptions.SelectException;

public class QueryOnDatabase {
	
	/*
	 * run the create statement directly in the database
	 * @param query create statement
	 */
	public static boolean runCreate(String query) throws CreateException {
		Connection conn;
		try {
			conn = ConnectDatabase.connect();
			Statement st = conn.createStatement();
			st.executeUpdate(query);
			st.close();
			conn.close();
			return true;
		} catch (Exception e) {
			throw new CreateException();
		}
	}
	
	/*
	 * run the drop statement directly in the database
	 * @param query drop statement
	 */
	public static boolean runDrop(String query) throws DropException {
		try {
			return runCreate(query);
		} catch (CreateException e) {
			throw new DropException();
		}
	}
	
	/*
	 * insertColumn without giving constraint default varchar(255)
	 */
	public static boolean runInsertColumn(String columnName, String tableName) throws AlterException {
		return runInsertColumn(columnName, tableName, "varchar(255)");
	}
	
	/*
	 * insertColumn with contraint given
	 */
	public static boolean runInsertColumn(String columnName, String tableName, String constraint) throws AlterException {
		String sql = "alter table " + tableName 
				+ " add column " + columnName + " "+ constraint + ";";
		Connection conn;
		try {
			conn = ConnectDatabase.connect();
			Statement st = conn.createStatement();
			st.execute(sql);
			st.close();
			conn.close();
			return true;
		} catch (Exception e) {
			throw new AlterException(tableName, columnName);
		}
	}
	
	/*
	 * formulate a list of String into a single string with comma separate them
	 * and each element in the list will wrap with quote
	 */
	private static String formulateString(List<String> listOfString, char quote) {
		String result = "";
		for (String s : listOfString) {
			result = result + quote + s + quote +",";
		}
		return "(" + result.substring(0, result.length()-1) + ")";
	}
	
	/*
	 * formulate a list of String into a single string with comma separate them
	 * and each element in the list will wrap with single quotation mark (')
	 */
	private static String formulateData(List<String> data) {
		return formulateString(data, '\'');
	}
	
	/*
	 * formulate a list of String into a single string with comma separate them
	 * and each element in the list will wrap with back quotation mark (`)
	 */
	private static String formulateIds(List<String> columnIds) {
		return formulateString(columnIds, '`');
	}
	
	/*
	 * insert Row given query String. the columnIds and the data should match each other.
	 * @param tableName table name of the table in the database
	 * @param columnIds in the form of (columnId1, columnId2, columnId3)
	 * @param data in the form of (data1, data2, data3)
	 */
	public static int insertRowRawQuery(String tableName, String columnIds, String data) throws InsertException {
		String sql = "insert into " + tableName + columnIds 
				+ " values " + data + ";" ;
		Connection conn;
		try {
			conn = ConnectDatabase.connect();
			Statement st = conn.createStatement();
			int id = st.executeUpdate(sql);
			st.close();
			conn.close();
			return id;
		} catch (Exception e) {
			throw new InsertException(tableName, data);
		}
	}
	/*
	 * insert data into specific table (columnIds) given
	 * columnIds and data must match
	 */
	public static int insertRow(String tableName, List<String> columnIds, List<String> data) throws InsertException {
		
		// formulate the columnIds into the one single string with comma separate
		String formulatedColumnId = formulateIds(columnIds);
		String formulatedData = formulateData(data);
		return insertRowRawQuery(tableName, formulatedColumnId, formulatedData);
	}

	

	/*
	 * insert variableName mapping to variableName table
	 */
	public static int insertVariableName(String columnId, String columnName) throws InsertException {
		List<String> data = new ArrayList<String>();
		data.add(columnId);
		data.add(columnName);
		String formulatedData = formulateData(data);
		return insertRowRawQuery("VariableName", "(variableName, realName)", formulatedData);
	}
	
	/*
	 * select all the data under the columnId in the table tableName
	 */
	public static List<String> selectCol(String tableName, String columnId) throws SelectException{
		return selectColConstraint(tableName, columnId, "");
	}
	
	/*
	 * select all the data under the columnId and satisfies the specific constraint
	 */
	public static List<String> selectColConstraint(String tableName, String columnId, String constraint) throws SelectException {
		String sqlNoConstraint = "select " + columnId + " from " + tableName;
		String sqlWithConstraint = sqlNoConstraint + " where " + constraint + ";";
		Connection conn;
		List<String> column = new ArrayList<String>();
		try {
			conn = ConnectDatabase.connect();
			Statement st = conn.createStatement();
			ResultSet rs;
			if (constraint== "") {
				rs = st.executeQuery(sqlNoConstraint);
			} else {
				rs = st.executeQuery(sqlWithConstraint);
			}
			while (rs.next()) {
				String columnVal = rs.getString(columnId);
				column.add(columnVal);
			}
			st.close();
			conn.close();
			return column;
		} catch (Exception e) {
			if (constraint== "") {
				throw new SelectException(sqlNoConstraint);
			} else {
				throw new SelectException(sqlWithConstraint);
			}
		}
	}
	
	
	/*
	 * create an empty table with id as primary key
	 * @param attrs attributes with constraints
	 */
	public static boolean createTable(String tableName, String attrs) throws CreateException {
		String sql = "create table " + tableName + " ( " + attrs + " );" ;
		try {
			return runCreate(sql);
		} catch (CreateException e) {
			throw new CreateException(tableName);
		}
	}
	
	/*
	 * create an empty table that only contain the id attribute as primary key
	 * id is type int auto_inrement
	 */
	public static boolean createEmptyTable(String tableName) throws CreateException {
		String sql = "create table " + tableName + " ("
					+ " id int not null auto_increment primary key);";
		try {
			return runCreate(sql);
		} catch (CreateException e) {
			throw new CreateException(tableName);
		}
	}
	
	
	public static List<String> getColumnIds(String tableName) {
		String sql = "select * from " + tableName + ";";
		Connection conn;
		List<String> columnIds = new ArrayList<String>();
		try {
			conn = ConnectDatabase.connect();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
			int numOfColumn = rsmd.getColumnCount();
			for (int i = 1; i <= numOfColumn; i++) {
				String name = rsmd.getColumnName(i);
				columnIds.add(name);
			}
			st.close();
			conn.close();
			return columnIds;
		} catch (Exception e) {
			return null;
		}
	}


	public static boolean createTemplate(String temName, String tableName, List<String> columnIds, List<String> columnNames) throws InsertException, CreateException, AlterException {
		// store the template's real name and table name in the database
		List<String> attrNames = new ArrayList<String>();
		attrNames.add("templateName");
		attrNames.add("tableName");
		
		List<String> vals = new ArrayList<String>();
		vals.add(temName);
		vals.add(tableName);
		int id = insertRow("Template", attrNames, vals);
		System.out.println(id);
//		exception here
		
		// create an empty table 
		boolean createRes = createEmptyTable(tableName);
		// insert all the columnIds to the empty table
		int numOfColumn = columnIds.size();
		boolean insertColRes = true;
		for (int i = 0; i<numOfColumn; i++) {
			insertColRes = runInsertColumn(columnIds.get(i), tableName); // constraint is not set for now
			// if any error happens, insertColRes will be false
			// need to throw exception here
			if (!insertColRes) {
				
				break;
			}
		}

		// store all the columnIds and columnNames into the variableName table in database
		for (int i = 0; i < numOfColumn; i++) {
			int res = insertVariableName(columnIds.get(i), columnNames.get(i));

			if (res < 0) {
				System.out.println("insert error" + columnIds.get(i) + " " + columnNames.get(i));
				break;
			}
		}
		
		return (createRes && insertColRes);
	}
}