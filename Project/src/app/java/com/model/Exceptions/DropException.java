package app.java.com.model.Exceptions;


/*
 * to be thrown when drop a table failed
 */
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
}
