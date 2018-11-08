package app.java.com.model.database.api;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import app.java.com.model.Exceptions.SelectException;

public class SelectCommand extends Command {

	private List<String> target;
	private String tableName;
	private List<String> constraints;
	private HashMap<String, String> IdConstraint;
	private List<String> ids;
	
	public SelectCommand() {}
	/*
	 * constructor used when want to execute
	 * select * from tableName;
	 */
	public SelectCommand(String tableName) {
		this.target = new ArrayList<>();
		this.tableName = tableName;
		this.constraints = new ArrayList<>();
	}
	
	/*
	 * constructor used when want to execute
	 * select * from tableName where constraints;
	 */
	public SelectCommand(String tableName, List<String> constraint) {
		this.target = new ArrayList<>();
		this.tableName = tableName;
		this.constraints = constraint;
	}
	
	public SelectCommand(List<String> target, String tableName) {
		this.tableName = tableName;
		this.target = target;
		this.constraints = new ArrayList<>();
	}
	/*
	 * constructor used when want to execute
	 * select target from tableName where constraints
	 */
	public SelectCommand(List<String> target, String tableName, List<String> constraint) {
		this.target = target;
		this.tableName = tableName;
		this.constraints = constraint;
	}
	
	/*
	 * set a list of columnId from the given tableName
	 */
	public void setColumnIds() throws SelectException {
		String sql = "select * from " + tableName + ";";
		Connection conn;
		List<String> columnIds = new ArrayList<>();
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
			this.ids = columnIds;
		} catch (Exception e) {
			throw new SelectException(tableName);
		}
	}
	
	public List<String> getColumnIds() throws SelectException {
		if (this.ids == null) {
			this.setColumnIds();
		}
		return this.ids;
	}
	
	/*
	 * get a list of columnConstraint given the tableName
	 */
	public List<String> getConstraints() throws SelectException {
		String sql = "select DATA_TYPE from information_schema.columns "
				+ "where table_name = '" + tableName + "';";
		Connection conn;
		List<String> constraints = new ArrayList<>();
		try {
			conn = ConnectDatabase.connect();
			Statement st = conn.createStatement();
			ResultSet rs;
			rs = st.executeQuery(sql);
			while (rs.next()) {
				String constraint = rs.getString("DATA_TYPE");
				constraints.add(constraint);
			}
			st.close();
			conn.close();
			
			return constraints;
		} catch (Exception e) {
			throw new SelectException(tableName);
		}
	}
	
	
	/*
	 * no need to set the IdConstraintMap every time we use the select command
	 * but if we need to 
	 */
	public void setIdConstraintMap() throws SelectException {
		HashMap<String, String> map = new HashMap<String, String>();
		List<String> ids= this.getColumnIds();
		List<String> constraints = this.getConstraints();
		int size = ids.size();
		for (int i = 0; i<size; i++) {
			map.put(ids.get(i), constraints.get(i));
		}
		this.IdConstraint = map;
	}
	
	public HashMap<String, String> getIdConstraintMap() {
		if (this.IdConstraint == null) {
			try {
				setIdConstraintMap();
			} catch (SelectException e) {
				System.out.println(e.getMessage());
				return null;
			}
		}
		return this.IdConstraint;
	}
	
	/*
	 * return [ [1st row], [2nd row], ...]
	 * given the target, tableName, constraints
	 * (eg. target = [colId1, colId2, colId3,...]) colId must be in the table
	 */
	public List<List<String>> selectHandle() throws SelectException {
		String formulatedTarget = "";
		// if the target is still empty then by default select *
		if (target.isEmpty()) {
			formulatedTarget = "*";
			this.target = this.getColumnIds();
		} else {
			// need to check if the targets are in this table
			for (String t : target) {
				if (!this.getColumnIds().contains(t)) {
                    System.out.println("Still going here " + t);
					throw new SelectException(tableName);
				}
			}
			
			String formulatedIds = formulateIds(target);
			formulatedTarget = formulatedIds.substring(1, formulatedIds.length()-1);
		}
		
		String sqlNoConstraint = "select " + formulatedTarget + " from " + tableName;
		String sqlWithConstraint = sqlNoConstraint + " where ";

		for(int index = 0; index < constraints.size() - 1; index++) {
		    sqlWithConstraint += constraints.get(index) + " AND ";
        }

        if(constraints.size() > 0) {
            sqlWithConstraint += constraints.get(constraints.size() - 1) + ";";
        }

        System.out.println(sqlWithConstraint);
		Connection conn;
		List<List<String>> data = new ArrayList<List<String>>();
		try {
			conn = ConnectDatabase.connect();
			Statement st = conn.createStatement();
			ResultSet rs;
			if (constraints.isEmpty()) {
				rs = st.executeQuery(sqlNoConstraint + ";");
			} else {
				rs = st.executeQuery(sqlWithConstraint);
			}

			while (rs.next()) {
				List<String> row = new ArrayList<>();
				for (String tar : target) {
					String val = rs.getString(tar);
					row.add(val);
					
				}
				data.add(row);
			}

			System.out.println(data);
			st.close();// select name,apsw rd from account where userName = .. AND
			conn.close();
			return data;
		} catch (Exception e) {
			System.out.println("except");
			if (constraints.isEmpty()) {
				throw new SelectException(sqlNoConstraint);
			} else {
				throw new SelectException(sqlWithConstraint);
			}
		}
	}
	
	@Override
	public boolean handle() throws SelectException {
		selectHandle();
		return true;
	}
}
