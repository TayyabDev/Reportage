package app.java.com.model.Exceptions;

import java.util.List;

public class InvalidException extends InsertException {

	private List<String> invalideVal;
	private String table;
	private String reason;
	
	public InvalidException() {
		super();
	}
	public InvalidException(String table, List<String> invalideVal) {
		this.invalideVal = invalideVal;
		this.table = table;
	}
	public InvalidException(String table, List<String> invalideVal, String reason) {
		this.invalideVal = invalideVal;
		this.table = table;
		this.reason = reason;
	}

	@Override
	public String getMessage(){
		String message = "Inserting invalid input " + invalideVal + " into "
				+ table + ". reason: " + this.reason;
		return message;
	}
}
