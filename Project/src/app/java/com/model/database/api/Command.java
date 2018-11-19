package app.java.com.model.database.api;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import app.java.com.model.Exceptions.ConnectionFailedException;

public abstract class Command {

	/*
	 * need to implement for specific Command
	 */
	public abstract boolean handle() throws Exception;
	
	/*
	 * formulate a list of String into a single string with comma separate them
	 * and each element in the list will wrap with quote
	 */
	private static String formulateString(List<String> listOfString, char quote) {
		String result = "";
		for (String s : listOfString) {
			result = result + quote + s + quote +",";
		}

		if(result.length() == 0) {
			return "";
		}

		return "(" + result.substring(0, result.length()-1) + ")";
	}
	
	/*
	 * formulate a list of String into a single string with comma separate them
	 * and each element in the list will wrap with single quotation mark (')
	 */
	public static String formulateData(List<String> data) {
		return formulateString(data, '\'');
	}
	
	/*
	 * formulate a list of String into a single string with comma separate them
	 * and each element in the list will wrap with back quotation mark (`)
	 */
	public static String formulateIds(List<String> columnIds) {
		return formulateString(columnIds, '`');
	}
	
	/*
	 * run Insert/Drop/Update/Alter commands directly in the database
	 * @param query create statement
	 */
	public int runExecuteUpdate(String query) throws SQLException, ConnectionFailedException {
		Connection conn;
		ResultSet rs;
		int lastRow = -1;
		try {
			conn = ConnectDatabase.connect();
		} catch (Exception e) {
			throw new ConnectionFailedException();
		}
		Statement st = conn.createStatement();
		int res = st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
	    if (res != -1) {
			rs = st.getGeneratedKeys();
		    if (rs.next()) {
		        lastRow = rs.getInt(Statement.RETURN_GENERATED_KEYS);
		    }
	    }
		st.close();
		conn.close();
		return lastRow;
	}
	
	/*
	 * insert Column with contraint given
	 */
	public boolean runExecute(String sql) throws Exception {
		Connection conn;
		conn = ConnectDatabase.connect();
		Statement st = conn.createStatement();
		st.execute(sql);
		st.close();
		conn.close();
		return true;

	}
	
	/*
	 * select all the data under the columnId and satisfies the specific constraint
	 */
	public static String runExecuteQuery(String sql) throws Exception {
		Connection conn;
        String report = "";
        int column = 0;
        
		conn = ConnectDatabase.connect();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        
        // Get the column name
		for (int i = 1; i <= columnsNumber; i++) {
			String name = rsmd.getColumnName(i);
			report += name;
			report += ",";
		}
		report = report.substring(0, report.length()-1);
    	report += "\n";
        
		// Get column values
        while (rs.next()) {
        	while (column < columnsNumber) {
        		report += rs.getString(column + 1);
        		report += ",";
        		column++;
        	}
        	report = report.substring(0, report.length()-1);
        	report += "\n";
        	column = 0;
        }
		
		st.close();
		conn.close();
		return report;
		
	}
}
