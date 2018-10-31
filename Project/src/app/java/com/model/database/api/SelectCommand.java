package app.java.com.model.database.api;

public class SelectCommand extends Command {

	private String target;
	private String tableName;
	private String constraint;
	
	/*
	 * constructor without constraint
	 */
	public SelectCommand(String tableName) {
		this.target = "*";
		this.tableName = tableName;
		this.constraint = null;
	}
	
	public SelectCommand(String tableName, String constraint) {
		this.target = "*";
		this.tableName = tableName;
		this.constraint = constraint;
	}
	
	public SelectCommand(String target, String tableName, String constraint) {
		this.target = target;
		this.tableName = tableName;
		this.constraint = constraint;
	}
	
	public boolean handle() {
		
	}
	
}
