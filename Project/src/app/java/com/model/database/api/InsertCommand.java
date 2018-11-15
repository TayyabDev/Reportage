package app.java.com.model.database.api;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import app.java.com.model.Exceptions.DuplicateKeyException;
import app.java.com.model.Exceptions.InsertException;

public class InsertCommand extends Command {

	private String tableName;
	private List<String> attrs;
	private List<String> vals;
	
	public InsertCommand(String tableName, List<String> attrs, List<String> vals) {
		this.tableName = tableName;
		this.attrs = attrs;
		this.vals = new ArrayList<>();
		for(String v : vals) {
			this.vals.add(formatVal(v));
		}
	}
	
	public void addAttrVal(String attr, String val) {
		this.attrs.add(attr);
		this.vals.add(formatVal(val));
	}
	
	public static String formatVal(String val) {
		return val.replaceAll("\\s+", " ").trim();
	}
	
	@Override
	public boolean handle() throws InsertException {
		insertHandle();
		return true;
	}

	public int insertHandle() throws InsertException {
		String formulatedIds = formulateIds(attrs);
		String formulatedData = formulateData(vals);
		String sql = "insert into " + tableName + formulatedIds
				+ "values " + formulatedData +";";
		try {
			return runExecuteUpdate(sql);
		} catch (SQLIntegrityConstraintViolationException e){
			throw new DuplicateKeyException(formulatedData);
		} catch (Exception e) {
			throw new InsertException(tableName, formulatedData);
		}
	}
}
