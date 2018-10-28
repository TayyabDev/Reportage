package app.java.com.model.Exceptions;

/*
 * to be thrown when select failed
 */
public class SelectException extends Exception {

	private String table;
	
	
	public SelectException() {
		super();
	}
	public SelectException(String table) {
		this.table = table;
	}
	
	
}
