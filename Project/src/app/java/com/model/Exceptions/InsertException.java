package app.java.com.model.Exceptions;

import java.util.List;

/*
 * to be thrown when insert val to table failed
 */
@SuppressWarnings("serial")
public class InsertException extends Exception {

	private String table;
	private String val;
	private boolean isFixed;
	@SuppressWarnings("unused")
	private String type;


	public InsertException() {
		super();
		isFixed = false;
	}


	public InsertException(String table, String val) {
		this.table = table;
		this.val = val;
		isFixed = false;
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
	public String getMessage() {
		String message = "error when insert new row " + this.val + ".";
		return message;
	}

	public void setFixed(boolean fixed) {
		isFixed = fixed;
	}

	public boolean getIsFixed() {
		return isFixed;
	}

	public String getType() {
		return "InsertException";
	}

	public List<String> getErrorValues() {
		return null;
	}



}
