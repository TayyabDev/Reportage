package com.app.model.database.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDatabase {
	private static Connection conn;
	
	public static Connection connect() throws Exception{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new Exception("No database");
		}
		String connectionURL = "jdbc:mysql://mathlab.utsc.utoronto.ca:3306/cscc43s18_linjun9?serverTimezone=UTC&useSSL=False";
		conn = DriverManager.getConnection(connectionURL, "linjun9", "linjun9");
		return conn;
	}
	
	public static void close(){
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
