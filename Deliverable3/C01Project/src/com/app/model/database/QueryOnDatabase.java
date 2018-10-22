package com.app.model.database;

import java.sql.Connection;
import java.sql.Statement;

public class QueryOnDatabase {
	
	/*
	 * run the create statement directly in the database
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
//			e.printStackTrace();
			return false;
		}
	}
	
	/*
	 * run the drop statement directly in the database
	 */
	public static boolean runDrop(String query) {
		return runCreate(query);
	}
	
	
}
