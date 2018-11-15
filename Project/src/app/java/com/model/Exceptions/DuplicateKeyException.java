package app.java.com.model.Exceptions;

public class DuplicateKeyException extends InsertException{
	
	private String key;
	
	public DuplicateKeyException() {
		super();
	}
	public DuplicateKeyException(String key) {
		this.key = key;
	}

	@Override
	public String getMessage(){
		String message = key + " already exists.";
		return message;
	}
}
