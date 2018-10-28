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
    	try {
			QueryOnDatabase.runCreate(query);
		} catch (CreateException e) {
			templateResultInterface.onErrorCreateTemplate("failed when create " + e.getTable());
		}
        // Look at templateResultInterface for communication back with the presenter
		templateResultInterface.onSuccessCreateTemplate("success");
    }

    @Override
    public void createUsingFile(CreateTemplateResultInterface templateResultInterface, String fileName) {
        ExcelFile exc = new ExcelFile(fileName);
        
        // ask ui for specific sheet number 
        // for now by default the excel file only contain 1 sheet
        String temName = exc.getTemplateName(2);
        List<String> columnIds = exc.getSheetColumnIds(2);
        List<String> columnNames = exc.getSheetColumnNames(2);
        
        // using the sheet Name as the table name in the database
        String sheetName = exc.getSheetName(2);
        String tableName = sheetName.replace(' ', '_');
        
        try {
			QueryOnDatabase.createTemplate(temName, tableName, columnIds, columnNames);
		} catch (InsertException | CreateException | AlterException e) {
			// TODO Auto-generated catch block
			templateResultInterface.onErrorCreateTemplate("failed");
		}
        templateResultInterface.onSuccessCreateTemplate("success");
        
        // Look at templateResultInterface for communication back with the presenter
    }
}
