package app.java.com.model.database.api;

import java.util.ArrayList;
import java.util.List;

import app.java.com.model.Exceptions.InsertException;

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
		String formulatedIds = formulateIds(attrs);
		String formulatedData = formulateData(vals);
		String sql = "insert into " + tableName + formulatedIds
				+ "values " + formulatedData +";";
		try {
			return runExecuteUpdate(sql);
		} catch (Exception e) {
		    e.printStackTrace();
			throw new InsertException(tableName, formulatedData);
		}
		
	}


}
