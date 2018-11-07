package app.java.com.model.utilities.templateFile;

import java.util.List;

import app.java.com.model.entities.template.Template;

public interface TemplateFileInterface {
	
	/**
	 * Get the Template the contains the Template Name, Table Name,
	 * Column Ids, and Column Name.
	 * @return Returns a Template object with all the identifing details of the file.
	 */
	Template getTemplateNameColumns();

	/**
	 * Get the name of the template.
	 * @return returns a String of the template name.
	 */
	String getTemplateName();
	
	/**
	 * Get the name of the table to store the template as.
	 * @return returns a String of the table name.
	 */
	String getTableName();

	/**
	 * Gets a list of column ids from the file.
	 * @return returns a List of Strings of the column ids in the file.
	 */
	List<String> getColumnIds();

	/**
	 * Get a list of column names from the file.
	 * @return returns a List of String of the column names in the file.
	 */
	List<String> getColumnNames();

	/**
	 * Get the number of columns from the file
	 * @return returns an int of the number of columns in the file.
	 */
	int getNumColumns();

	/**
	 * Get the data of row rowNum in the file
	 * @param rowNum the index of the row to get data of (first entry is row 3).
	 * @return returns a List of Strings of all the data of rowNum in the file
	 */
	List<String> getRow(int rowNum);

	/**
	 * Get the number of rows of sthe file, only includes data.
	 * Currently returns the total number for rows in the file, which does not
	 * discard empty rows.
	 * @return returns an int of the number of rows of sheetNum.
	 */
	int getNumRows();
}
