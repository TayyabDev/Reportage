package app.java.com.model.entities.template;

import java.util.List;

public class TemplateOptions {
	
	private List<String> columnIds;
	private List<String> columnTypes;
	private List<List<String>> columnOptions;
	
	public TemplateOptions (List<String> columnIds, List<String> columnTypes, List<List<String>> columnOptions) {
		this.columnIds = columnIds;
		this.columnTypes = columnTypes;
		this.columnOptions = columnOptions;
	}
	
	public List<String> getColumnIds() {
		return this.columnIds;
	}
	
	public List<String> getColumnType() {
		return this.columnTypes;
	}
	public List<List<String>> getColumnOptions() {
		return this.columnOptions;
	}
}
