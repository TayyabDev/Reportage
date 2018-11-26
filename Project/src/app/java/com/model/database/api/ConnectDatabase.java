package app.java.com.model.database.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import app.java.com.model.Exceptions.ConnectionFailedException;

public class ConnectDatabase {
	
	private static final String CONNECTION_URL = "jdbc:mysql://localhost/cscc01?serverTimezone=UTC&useSSL=False";
//	private static final String CONNECTION_URL = "jdbc:mysql://mathlab.utsc.utoronto.ca:3306/cscc43s18_linjun9?serverTimezone=UTC&useSSL=False";
	private static final String USERNAME = "root";
//	private static final String USERNAME = "linjun9";
	private static final String PASSWORD = "";
//	private static final String PASSWORD = "linjun9";
	private static Connection conn;

	public static Connection connect() throws ConnectionFailedException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new ConnectionFailedException();
		}
		try {
			conn = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			throw new ConnectionFailedException();
		}
		return conn;
	}

	public static void close() throws ConnectionFailedException {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new ConnectionFailedException();
			}
		}
	}
}
