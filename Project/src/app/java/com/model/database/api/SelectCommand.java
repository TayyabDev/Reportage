package app.java.com.model.database.api;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
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
	 * constructor used when want to execute select * from tableName;
	 */
	public SelectCommand(String tableName) {
		this.target = new ArrayList<String>();
		this.tableName = tableName;
		this.constraints = new ArrayList<>();
	}

	/*
	 * constructor used when want to execute select * from tableName where constraints;
	 */
	public SelectCommand(String tableName, List<String> constraint) {
		this.target = new ArrayList<>();
		this.tableName = tableName;
		this.constraints = constraint;
	}

	/*
	 * select tar1,tar2,... from tableName;
	 */
	public SelectCommand(List<String> target, String tableName) {
		this.tableName = tableName;
		this.target = target;
		this.constraints = new ArrayList<>();
	}

	/*
	 * constructor used when want to execute select target from tableName where constraints
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
				+ "where table_name = '"
				+ tableName
				+ "';";
		Connection conn;
		List<String> constraints = new ArrayList<String>();
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
	 * no need to set the IdConstraintMap every time we use the select command but if we need to
	 */
	public void setIdConstraintMap() throws SelectException {
		HashMap<String, String> map = new HashMap<String, String>();
		List<String> ids = this.getColumnIds();
		List<String> constraints = this.getConstraints();
		int size = ids.size();
		for (int i = 0; i < size; i++) {
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

	public List<String> selectHandleSingle() throws SelectException {
		if (!(this.target.size() == 1)) {
			throw new SelectException();
		}
		List<String> res = new ArrayList<>();
		String id = this.target.get(0);
		String sqlNoConstraint = "select " + id + " from " + tableName + ";";
		String sqlWithConstraint = "";
		try {
			Connection conn = ConnectDatabase.connect();;
			Statement st = conn.createStatement();
			ResultSet rs;
			if (this.constraints.isEmpty()) {
				rs = st.executeQuery(sqlNoConstraint);
			} else {
				sqlWithConstraint = "select " + id + " from " + tableName + " where ";
				for (int index = 0; index < constraints.size() - 1; index++) {
					sqlWithConstraint += constraints.get(index) + " AND ";
				}
				if (constraints.size() > 0) {
					sqlWithConstraint += constraints.get(constraints.size() - 1) + ";";
				}
				rs = st.executeQuery(sqlWithConstraint);

			}
			while (rs.next()) {
				String val = rs.getString(id);
				res.add(val);
			}
			st.close();// select name,apsw rd from account where userName = .. AND
			conn.close();
		} catch (Exception e) {
			if (constraints.isEmpty()) {
				throw new SelectException(sqlNoConstraint);
			} else {
				throw new SelectException(sqlWithConstraint);
			}
		}
		return res;
	}

	/*
	 * return [ [1st row], [2nd row], ...] given the target, tableName, constraints (eg. target =
	 * [colId1, colId2, colId3,...]) colId must be in the table return [[1stRow], [2ndRow]...]
	 */
	public List<List<String>> selectHandle() throws SelectException {
		String formulatedTarget = "";
		List<String> sqlFunctions = Arrays.asList("count(*)","sum(*)","max(*)", "min(*)");
		// if the target is still empty then by default select *
		if (target.isEmpty()) {
			formulatedTarget = "*";
			this.target = this.getColumnIds();
		} else {
			// need to check if the targets are in this table
			for (String t : target) {
				if (!this.getColumnIds().contains(t) && !sqlFunctions.contains(t)) {
					throw new SelectException(tableName);
				}
			}

			String formulatedIds = "";

			if(target.size() == 1 && sqlFunctions.contains(target.get(0))) {
                formulatedTarget = target.get(0);
            } else {
                formulatedIds = formulateIds(target);
                formulatedTarget = formulatedIds.substring(1, formulatedIds.length() - 1);
            }
		}

		String sqlNoConstraint = "select " + formulatedTarget + " from " + tableName;
		String sqlWithConstraint = sqlNoConstraint + " where ";

		for (int index = 0; index < constraints.size() - 1; index++) {
			sqlWithConstraint += constraints.get(index) + " AND ";
		}
		if (constraints.size() > 0) {
			sqlWithConstraint += constraints.get(constraints.size() - 1) + ";";
		}
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
				List<String> row = new ArrayList<String>();
				for (String tar : target) {
					String val = rs.getString(tar);
					row.add(val);

				}
				data.add(row);
			}
			st.close();// select name,apsw rd from account where userName = .. AND
			conn.close();
			return data;
		} catch (Exception e) {
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

	public List<String> getPrimaryKeyColumn() throws SelectException {
		String sql = "select column_name from information_schema.columns "
				+ "where table_name = '"
				+ this.tableName
				+ "' and column_key = 'pri';";
		Connection conn;
		List<String> constraints = new ArrayList<String>();
		try {
			conn = ConnectDatabase.connect();
			Statement st = conn.createStatement();
			ResultSet rs;
			rs = st.executeQuery(sql);
			while (rs.next()) {
				String constraint = rs.getString("column_name");
				constraints.add(constraint);
			}
			st.close();
			conn.close();

			return constraints;
		} catch (Exception e) {
			throw new SelectException(tableName);
		}
	}

	public List<String> getColumns() throws SelectException {
		String sql = "select column_name from information_schema.columns "
				+ "where table_name = '"
				+ this.tableName
				+ "';";

		Connection conn;
		List<String> constraints = new ArrayList<String>();
		try {
			conn = ConnectDatabase.connect();
			Statement st = conn.createStatement();
			ResultSet rs;
			rs = st.executeQuery(sql);
			while (rs.next()) {
				String constraint = rs.getString("column_name");
				constraints.add(constraint);
			}
			st.close();
			conn.close();

			return constraints;
		} catch (Exception e) {
			throw new SelectException(tableName);
		}
	}

	public List<String> getValuesOfRowAtPrimaryKeys(int rowNum) throws SelectException {
		List<String> result = new ArrayList<String>();
		List<String> columns = this.getColumns();
		List<String> primaryKeys = this.getPrimaryKeyColumn();
		List<List<String>> tableData = this.selectHandle();
		List<String> row = tableData.get(rowNum);
		for (int i = 0; i < row.size(); i++) {
			if (primaryKeys.contains(columns.get(i))) {
				result.add(row.get(i));
			}
		}
		return result;
	}
}
