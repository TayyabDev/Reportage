package app.java.com.model.database.api;

import app.java.com.model.Exceptions.DropException;

public class DropCommand extends Command {

	private String tableName;

	/*
	 * to drop an empty table given the table name
	 */
	public DropCommand(String tableName) {
		this.tableName = tableName;
	}

	@Override
	public boolean handle() throws DropException {
		String sql = "drop table " + tableName + ";";
		try {
			runExecuteUpdate(sql);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new DropException(tableName);
		}
	}
}
