package app.java.com.model.Exceptions;

import java.util.List;

public class DuplicateKeyException extends InsertException {

	private List<String> duplicatedVals;
	private String table;
	private List<String> primaryKeyColumn;
	private boolean isFixed;
	private String message;

	public DuplicateKeyException() {
		super();
		isFixed = false;
	}

	public DuplicateKeyException(String table, List<String> duplicatedVals) {
		this.duplicatedVals = duplicatedVals;
		this.table = table;
        this.isFixed = false;
        this.message = duplicatedVals + " already exists in " + table + ".";
    }

	@Override
	public String getMessage() {
		return message;
	}

    @Override
    public String getTable() {
        return table;
    }

    public List<String> getDuplicatedVals() {
        return duplicatedVals;
    }

    @Override
	public String getType() {
		return "DuplicateKeyException";
	}

	@Override
    public List<String> getErrorValues() {
	    return duplicatedVals;
    }

}
