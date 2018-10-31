package app.java.com.model;


import java.util.List;

import app.java.com.model.Exceptions.AlterException;
import app.java.com.model.Exceptions.CreateException;
import app.java.com.model.database.api.QueryOnDatabase;
import app.java.com.model.interfaces.CreateTemplateModel;
import app.java.com.model.interfaces.CreateTemplateResultInterface;
import app.java.com.model.utilities.ExcelFile;

public class CreateTemplateModelImpl implements CreateTemplateModel {

    @Override
    /*
     * run Raw create Statement in database
     */
    public void runRawQuery(CreateTemplateResultInterface templateResultInterface, String query) {

    }

    @Override
    public void createUsingFile(CreateTemplateResultInterface templateResultInterface, String fileName) {
    	String formulatedFileName = fileName.replace("\\", "\\\\");
    	ExcelFile exc = new ExcelFile(formulatedFileName);
        
        // ask ui for specific sheet number 
        // for now by default the excel file only contain 1 sheet
        String temName = exc.getTemplateName(2);
        List<String> columnIds = exc.getSheetColumnIds(2);
        List<String> columnNames = exc.getSheetColumnNames(2);
        
        // using the sheet Name as the table name in the database
        String sheetName = exc.getSheetName(2);
        String tableName = "`" + sheetName.replace(' ', '_') + "`";
        boolean success = false;
        
		try {
			success = QueryOnDatabase.createTemplate(temName, tableName, columnIds, columnNames);
		} catch (AlterException | CreateException e) {
			templateResultInterface.onErrorCreateTemplate(e.getMessage());
		}
        if (success) {
        	templateResultInterface.onSuccessCreateTemplate("success");
        } 
        
        // Look at templateResultInterface for communication back with the presenter
    }
}
