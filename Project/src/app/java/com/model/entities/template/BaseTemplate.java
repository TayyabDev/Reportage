package app.java.com.model.entities.template;

import java.util.List;

public class BaseTemplate extends Template {

    private String templateName;
    private List<String> columnNames;
    private List<String> columnIds;
    private String tableName;

    public BaseTemplate(String templateName, List<String> templateColumnNames,
                            List<String> columnIds, String tableName) {
        this.templateName = templateName;
        this.columnNames = templateColumnNames;
        this.columnIds = columnIds;
        this.tableName = tableName;
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

    public String toString() {
    		String result = templateName + " - " + tableName + "\n";
    		result += columnIds.toString() + "\n";
    		result += columnNames.toString() + "\n";
        return result;
    }
}