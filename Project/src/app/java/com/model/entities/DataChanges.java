package app.java.com.model.entities;

public class DataChanges {

	private String tableName;
	private int row;
	private int column;
	private String oldValue;
	private String newValue;

	public DataChanges(String tableName, int row, int column, String oldValue, String newValue) {
		this.tableName = tableName;
		this.row = row;
		this.column = column;
		this.oldValue = oldValue;
		this.newValue = newValue;
	}

	public String getTableName() {
		return tableName;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public String getOldValue() {
		return oldValue;
	}

	public String getNewValue() {
		return newValue;
	}

	@Override
	public String toString() {
		return String.valueOf(
				row) + "," + String.valueOf(column) + " : " + oldValue + " - > " + newValue;
	}
}
