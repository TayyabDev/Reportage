package app.java.com.model;


import java.util.List;

import app.java.com.model.Exceptions.AlterException;
import app.java.com.model.Exceptions.CreateException;
import app.java.com.model.Exceptions.InsertException;
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
    	boolean success = false;
    	try {
			success = QueryOnDatabase.runCreate(query);
		} catch (CreateException e) {
			templateResultInterface.onErrorCreateTemplate("failed when create " + e.getTable());
		}
    	if (success) {
        // Look at templateResultInterface for communication back with the presenter
    		templateResultInterface.onSuccessCreateTemplate("success");
    	}
    }

    @Override
    public void createUsingFile(CreateTemplateResultInterface templateResultInterface, String fileName) {
    	String formulatedFileName = fileName.replace("\\", "\\\\");
    	ExcelFile exc = new ExcelFile(formulatedFileName);
        int sheetNum = 9;
//        if (exc.getNumSheets() > 1) {
//        	//ask ui for select
        
//        } 
    	
        // ask ui for specific sheet number 
        // for now by default the excel file only contain 1 sheet
        String temName = exc.getTemplateName(sheetNum);
        List<String> columnIds = exc.getSheetColumnIds(sheetNum);
        List<String> columnNames = exc.getSheetColumnNames(sheetNum);
        
        // using the sheet Name as the table name in the database
        String sheetName = exc.getSheetName(sheetNum);
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
