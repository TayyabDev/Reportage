package com.app.model.database.api;

import java.sql.Connection;
import java.sql.Statement;

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
			e.printStackTrace();
			return false;
		}
	}
	
//	public static List<String> selectAll(String tableName, String columnName) {
//		String sql = "select " + columnName + " from " + tableName;
//		
//	}
}
