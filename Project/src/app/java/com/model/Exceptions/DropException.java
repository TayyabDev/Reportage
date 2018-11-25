package app.java.com.model.Exceptions;


/*
 * to be thrown when drop a table failed
 */
@SuppressWarnings("serial")
public class DropException extends Exception {

	private String table;

	public DropException() {
		super();
	}

	public DropException(String table) {
		this.table = table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getTable() {
		return table;
	}

	@Override
	public String getMessage() {
		String message = "error when drop table " + this.table + ".";
		return message;
	}
}
