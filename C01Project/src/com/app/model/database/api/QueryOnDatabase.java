package com.app.model.database.api;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
	 * insert data into specific table (columnIds) given
	 * columnIds and data must match
	 */
	public static boolean insertRow(String tableName, List<String> columnIds, List<String> data) {
		
		// formulate the columnIds into the one single string with comma separate
		String formulatedColumnId = formulateString(columnIds);
		String formulatedData = formulateString(data);
		String sql = "insert into " + tableName + formulatedColumnId 
				+ " values " + formulatedData + ";" ;
		Connection conn;
		try {
			conn = ConnectDatabase.connect();
			Statement st = conn.createStatement();
			st.executeUpdate(sql);
			st.close();
			conn.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * insert variableName mapping to variableName table
	 */
	public static boolean insertVariableName(String columnId, String columnName) {
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
	 */
	public static boolean createTable(String tableName) {
		String sql = "create table " + tableName + " ("
				+ " id int not null auto_increment primary key);";
		return runCreate(sql);
	}
	
	
}
