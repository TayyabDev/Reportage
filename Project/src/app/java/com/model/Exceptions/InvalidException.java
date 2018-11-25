package app.java.com.model.Exceptions;

import java.util.List;

@SuppressWarnings("serial")
public class InvalidException extends InsertException {

	private List<String> invalidVal;
	@SuppressWarnings("unused")
	private String table;
	private String reason;

	public InvalidException() {
		super();

	}
	public InvalidException(String table, List<String> invalidVal) {
		this.invalidVal = invalidVal;
		this.table = table;
		this.reason = "Inserting invalid input " + this.invalidVal + " into " + table + ". reason: " + this.reason;
	}
	public InvalidException(String table, List<String> invalidVal, String reason) {
		this.invalidVal = invalidVal;
		this.table = table;
		this.reason = reason;
	}

	@Override
	public String getMessage(){
		return reason;
	}

	@Override
    public String getType() {
	    return "InvalidException";
    }

    @Override
    public List<String> getErrorValues() {
	    return invalidVal;
    }

}
