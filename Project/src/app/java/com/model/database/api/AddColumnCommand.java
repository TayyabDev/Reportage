package app.java.com.model.database.api;

import java.util.List;

import app.java.com.model.Exceptions.AlterException;

public class AddColumnCommand extends Command{

	private String tableName;
	private List<String> newCols;
	private List<String> constraints;
	/*
	 * used for insert new column
	 */
	public AddColumnCommand(String tableName, List<String> newCols, List<String> constraints) {
		this.tableName = tableName;
		this.newCols = newCols;
		this.constraints = constraints;
	}

	public void addNewColCons(String colId, String colCons) {
		this.newCols.add(colId);
		this.constraints.add(colCons);
	}
	
	public String matchColsAndCons() {
		int s = newCols.size();
		String res = "";
		if(s == 0) {
			return res;
		}
		for (int i = 0; i < s; i++) {
			String col = newCols.get(i);
			String cons = constraints.get(i);
			res = res + "add column " + col + " " + cons + ", ";
		}
		return res.substring(0, res.length()-2);
	}
	
	@Override
	public boolean handle() throws AlterException {
		String addColCommand = "";
		try {
			addColCommand = matchColsAndCons();
			String sql = "alter table " + tableName + " "
					+ addColCommand + ";";
			return runExecute(sql);
		} catch (Exception e) {
			throw new AlterException(tableName, addColCommand);
		}
	}
}
