package app.java.com.model.Exceptions;

@SuppressWarnings("serial")
public class ConnectionFailedException extends Exception {

	public ConnectionFailedException() {
		super();
	}
	
	public ConnectionFailedException(String errorMessage) {
		super(errorMessage);
	}
}
