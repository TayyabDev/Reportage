package app.java.com.model.database.api;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import app.java.com.model.Exceptions.ConnectionFailedException;
import app.java.com.model.Exceptions.DuplicateKeyException;
import app.java.com.model.Exceptions.InsertException;
import app.java.com.model.Exceptions.InvalidException;

public class InsertCommand extends Command {

	private String tableName;
	private List<String> attrs;
	private List<String> vals;

	public InsertCommand(String tableName, List<String> attrs, List<String> vals) {
		this.tableName = tableName;
		this.attrs = attrs;
		this.vals = vals;
	}

	public void addAttrVal(String attr, String val) {
		this.attrs.add(attr);
		this.vals.add(val);
	}

	@Override
	public boolean handle() throws InsertException {
		insertHandle();
		return true;
	}

	public int insertHandle() throws InsertException {
		String formulatedIds = formulateIds(attrs);
		String formulatedData = formulateData(vals);
		String sql = "insert into " + tableName + formulatedIds + "values " + formulatedData + ";";
		System.out.println(sql + " is the query");
		try {
			return runExecuteUpdate(sql);
		} catch (SQLIntegrityConstraintViolationException e) {
			throw new DuplicateKeyException(tableName, vals);
		} catch (SQLException e) {
			throw new InvalidException(tableName, vals, e.getMessage());
		} catch (ConnectionFailedException e) {
			throw new InsertException(tableName, formulatedData);
		}
	}
}
