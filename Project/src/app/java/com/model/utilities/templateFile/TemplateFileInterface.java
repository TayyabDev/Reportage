package app.java.com.model.utilities.templateFile;

import java.util.List;

import app.java.com.model.entities.template.Template;

public interface TemplateFileInterface {
	
	Template getFileAsTemplate();

	String getTemplateName();

	String getTableName();

	List<String> getColumnIds();

	List<String> getColumnNames();

	int getNumColumns();

	List<String> getRow(int rowNum);

	int getNumRows();
}
