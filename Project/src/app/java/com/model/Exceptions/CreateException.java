package app.java.com.model.Exceptions;


/*
 * to be thrown when create a new table failed
 */
public class CreateException extends Exception {

	private String table;
	
	public CreateException() {
		super();
	}
	public CreateException(String table) {
		this.table = table;
	}

	public void setTable(String table) {
		this.table = table;
	}
	public String getTable() {
		return table;
	}
}
