package app.java.com.model.Exceptions;

import java.util.List;

public class InvalidException extends InsertException {

	private List<String> invalideVal;
	private String table;
	
	public InvalidException() {
		super();
	}
	public InvalidException(String table, List<String> invalideVal) {
		this.invalideVal = invalideVal;
		this.table = table;
	}

	@Override
	public String getMessage(){
		String message = "Inserting invalid input " + invalideVal + " into "
				+ table + ".";
		return message;
	}
}
