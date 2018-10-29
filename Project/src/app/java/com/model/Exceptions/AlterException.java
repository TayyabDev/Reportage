package app.java.com.model.Exceptions;

/*
 * to be thrown when alter existing table(adding new column)
 */
public class AlterException extends Exception {
	
	private String table;
	private String newCol;
	
	public AlterException() {
		super();
	}
	public AlterException(String table, String newCol) {
		this.table = table;
		this.newCol = newCol;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public String getNewCol() {
		return newCol;
	}
	public void setNewCol(String newCol) {
		this.newCol = newCol;
	}
	@Override
	public String getMessage(){
		String message = "error when adding new column "+ this.newCol + " to table " + this.table + ".";
		return message;
	}


}
