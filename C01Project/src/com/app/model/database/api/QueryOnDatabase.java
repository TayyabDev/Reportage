package com.app.model.database.api;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class QueryOnDatabase {
	
	/*
	 * run the create statement directly in the database
	 * @param query create statement
	 */
	public static boolean runCreate(String query) {
		Connection conn;
		try {
			conn = ConnectDatabase.connect();
			Statement st = conn.createStatement();
			st.executeUpdate(query);
			st.close();
			conn.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/*
	 * run the drop statement directly in the database
	 * @param query drop statement
	 */
	public static boolean runDrop(String query) {
		return runCreate(query);
	}
	
	public static boolean runInsertColumn(String columnName, String tableName, String constraint) {
		if (constraint == "") {
			constraint = "varchar(255)";
		}
		String sql = "alter table " + tableName 
				+ " add column " + columnName + " "+ constraint;
		Connection conn;
		try {
			conn = ConnectDatabase.connect();
			Statement st = conn.createStatement();
			st.execute(sql);
			st.close();
			conn.close();
			return true;
		} catch (Exception e) {
//			e.printStackTrace();
			return false;
		}
	}
	
	/*
	 * formulate a list of String into a single string with comma separate them
	 */
	private static String formulateString(List<String> listOfString) {
		String result = "";
		for (String s : listOfString) {
			result = result + s + ", ";
		}
		return "(" + result.substring(0, result.length()) + ")";
	}
	
	
	/*
	 * insert Row given query String. the columnIds and the data should match each other.
	 * @param tableName table name of the table in the database
	 * @param columnIds in the form of (columnId1, columnId2, columnId3)
	 * @param data in the form of (data1, data2, data3)
	 */
	public static int insertRowRawQuery(String tableName, String columnIds, String data) {
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
			e.printStackTrace();
			return -1;
		}
	}
	/*
	 * insert data into specific table (columnIds) given
	 * columnIds and data must match
	 */
	public static int insertRow(String tableName, List<String> columnIds, List<String> data) {
		
		// formulate the columnIds into the one single string with comma separate
		String formulatedColumnId = formulateString(columnIds);
		String formulatedData = formulateString(data);
		return insertRowRawQuery(tableName, formulatedColumnId, formulatedData);
	}

	/*
	 * insert variableName mapping to variableName table
	 */
	public static int insertVariableName(String columnId, String columnName) {
		List<String> columns = new ArrayList<String>();
		columns.add("variableName");
		columns.add("realName");
		List<String> data = new ArrayList<String>();
		data.add(columnId);
		data.add(columnName);
		return insertRow("VariableName", columns, data);
	}
	
	
	public static List<String> selectColumnString(String tableName, String columnId, String constraint) {
		String sqlNoConstraint = "select " + columnId + " from " + tableName;
		String sqlWithConstraint = sqlNoConstraint + " where " + constraint;
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
			e.printStackTrace();
			return null;
		}
	}
	
	
	/*
	 * create an empty table with id as primary key
	 * @param attrs attributes with constraints
	 */
	public static boolean createTable(String tableName, String attrs) {
		String sql;
		if (attrs.compareTo("") == 0) {
			sql = "create table " + tableName + " ("
					+ " id int not null auto_increment primary key);";
		} else {
			sql = "create table " + tableName + " ( " + attrs + " );" ;
		}
		 
		return runCreate(sql);
	}
	
	
	public static List<String> getColumnIds(String tableName) {
		String sql = "select * from " + tableName;
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
			e.printStackTrace();
			return null;
		}
	}


	public static boolean createTemplate(String temName, String tableName, List<String> columnIds, List<String> columnNames) {
		// store the template's real name and table name in the database
		List<String> attrNames = new ArrayList<String>();
		attrNames.add("templateName");
		attrNames.add("tableName");
		
		List<String> vals = new ArrayList<String>();
		vals.add(temName);
		vals.add(tableName);
		int id = insertRow("Template", attrNames, vals);
//		exception here
		
		// create an empty table 
		boolean createRes = createTable(tableName, "");
		// insert all the columnIds to the empty table
		int numOfColumn = columnIds.size();
		boolean insertColRes = true;
		for (int i = 0; i<numOfColumn; i++) {
			insertColRes = runInsertColumn(columnIds.get(i), tableName, ""); // constraint is not set for now
			if (insertColRes) {
				break;
			}
		}
		
		
		// store all the columnIds and columnNames into the variableName table in database
		for (int i = 0; i < numOfColumn; i++) {
			int res = insertRowRawQuery(tableName, 
					"(variableName, realName)",
					"(" + columnIds.get(i) + ", " + columnNames.get(i) +")");

//			if (res < 0) {
//				throw new Exception();
//			}
		}
		
		return (createRes && insertColRes);
		
		
		
		
		
	}
	
	
}
