package app.java.com.model.Exceptions;

import java.util.List;

public class DuplicateKeyException extends InsertException{
	
	private List<String> duplicatedVals;
	private String table;
	
	public DuplicateKeyException() {
		super();
	}
	public DuplicateKeyException(String table, List<String> duplicatedVals) {
		this.duplicatedVals = duplicatedVals;
		this.table = table;
	}

	@Override
	public String getMessage(){
		String message = duplicatedVals + " already exists in "
				+ table + ".";
		return message;
	}
}
