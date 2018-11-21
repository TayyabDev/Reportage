package app.java.com.model.entities.template;

import java.util.List;

public class BaseTemplate extends Template {

    private String templateName;
    private List<String> columnNames;
    private List<String> columnIds;
    private String tableName;
    private List<String> requiredIds;

    public BaseTemplate(String templateName, List<String> templateColumnNames,
                        List<String> columnIds, String tableName) {
        this.templateName = templateName;
        this.columnNames = templateColumnNames;
        this.columnIds = columnIds;
        this.tableName = tableName;
        this.requiredIds = null;
    }
    
    public BaseTemplate(String templateName, List<String> templateColumnNames,
            List<String> columnIds, String tableName, List<String> requiredIds) {
		this.templateName = templateName;
		this.columnNames = templateColumnNames;
		this.columnIds = columnIds;
		this.tableName = tableName;
		this.requiredIds = requiredIds;
    }

    public String getTemplateName() {
    		
    		return templateName;
    }
    
    public List<String> getColumnNames() {

        return columnNames;
    }

    public List<String> getColumnIds() {
        return columnIds;
    }
    
    public String getTableName() {
    		return tableName;
    }
    
    public List<String> getRequiredIds() {
    	return requiredIds;
    }

    public String toString() {
    		String result = templateName + " - " + tableName + "\n";
    		result += columnIds.toString() + "\n";
    		result += columnNames.toString() + "\n";
    		if (requiredIds != null) result += requiredIds.toString() + "\n";
        return result;
    }
}