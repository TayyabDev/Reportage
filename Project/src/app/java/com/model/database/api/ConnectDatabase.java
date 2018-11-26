package app.java.com.model.database.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import app.java.com.model.Exceptions.ConnectionFailedException;

public class ConnectDatabase {
	private static Connection conn;

	public static Connection connect() throws ConnectionFailedException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new ConnectionFailedException();
		}
		String connectionURL =
				"jdbc:mysql://mathlab.utsc.utoronto.ca:3306/cscc43s18_linjun9?serverTimezone=UTC&useSSL=False";
		try {
			conn = DriverManager.getConnection(connectionURL, "linjun9", "linjun9");
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
