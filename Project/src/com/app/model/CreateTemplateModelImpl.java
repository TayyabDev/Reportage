package com.app.model;

import com.app.model.database.api.QueryOnDatabase;
import com.app.model.interfaces.CreateTemplateResultInterface;
import com.app.model.usecase.CreateTemplate;
import com.app.model.utilities.ExcelFile;

import java.io.File;
import java.util.List;

public class CreateTemplateModelImpl implements com.app.model.interfaces.CreateTemplateModel {

    @Override
    /*
     * run Raw create Statement in database
     */
    public void runRawQuery(CreateTemplateResultInterface templateResultInterface, String query) {
    	boolean success = QueryOnDatabase.runCreate(query);
        // Look at templateResultInterface for communication back with the presenter
    	if (success) {
    		templateResultInterface.onSuccessCreateTemplate("success");
    	} else {
    		templateResultInterface.onErrorCreateTemplate("failed");
    	}
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
        
        boolean success = QueryOnDatabase.createTemplate(temName, tableName, columnIds, columnNames);
        
        if (success) {
        	templateResultInterface.onSuccessCreateTemplate("success");
        } else {
        	templateResultInterface.onErrorCreateTemplate("failed");
        }
        
        // Look at templateResultInterface for communication back with the presenter
    }
}
