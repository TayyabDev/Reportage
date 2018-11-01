package app.java.com.model.utilities.createTemplate;

import app.java.com.model.entities.template.Template;

public interface CreateTemplateParamInterface {
	
	/**
	 * Takes in an excel or csv file and returns the template name, table name,
	 * column ids, and column names in a List.
	 * @param fileName the name of the file to get template of.
	 * @param sheetNum the sheet of the excel file to get the template of.
	 * @return returns the Template.
	 */
	Template getTemplateNameColumns(String fileName, int sheetNum);
}
