package app.java.com.model.Exceptions;

/*
 * to be thrown when select failed
 */
@SuppressWarnings("serial")
public class SelectException extends Exception {

	private String table;


	public SelectException() {
		super();
	}

	public SelectException(String table) {
		this.table = table;
	}

	@Override
	public String getMessage() {
		String message = "error when selecting from table " + this.table + ".";
		return message;
	}

}
