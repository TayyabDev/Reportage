package app.java.com.model.Exceptions;

/*
 * to be thrown when insert val to table failed
 */
public class InsertException extends Exception {

	private String table;
	private String val;
	
	public InsertException() {
		super();
	}
	
	public InsertException(String table, String val) {
		this.table = table;
		this.val = val;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public String getTable() {
		return table;
	}

	public String getVal() {
		return val;
	}
	@Override
	public String getMessage(){
		String message = "error when insert new row "+ this.val + " to table " + this.table + ".";
		return message;
	}
}
