package app.java.com.model.entities.template;

import java.util.List;

public class BaseTemplate extends Template {

    private String templateName;
    private List<String> columnNames;
    private List<String> columnIds;
    private String tableName;
    private List<String> requiredIds;
    private List<String> requiredColumnNames;

    public BaseTemplate(String templateName, List<String> templateColumnNames,
                        List<String> columnIds, String tableName) {
        this.templateName = templateName;
        this.columnNames = templateColumnNames;
        this.columnIds = columnIds;
        this.tableName = tableName;
        this.requiredIds = null;
        this.requiredColumnNames = null;
    }
    
    public BaseTemplate(String templateName, List<String> templateColumnNames,
            List<String> columnIds, String tableName, List<String> requiredIds,
            List<String> requiredColumnNames) {
		this.templateName = templateName;
		this.columnNames = templateColumnNames;
		this.columnIds = columnIds;
		this.tableName = tableName;
		this.requiredIds = requiredIds;
		this.requiredColumnNames = requiredColumnNames;
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
    
    public List<String> getRequiredColumnNames() {
    	return requiredColumnNames;
    }

    @Override
    public String toString() {
    		String result = templateName + " - " + tableName + "\n";
    		result += columnIds.toString() + "\n";
    		result += columnNames.toString() + "\n";
    		if (requiredIds != null) result += requiredIds.toString() + "\n";
    		if (requiredColumnNames != null) result += requiredColumnNames.toString() + "\n";
        return result;
    }
}