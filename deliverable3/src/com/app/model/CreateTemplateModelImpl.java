package com.app.model;

import com.app.model.database.api.QueryOnDatabase;
import com.app.model.interfaces.CreateTemplateResultInterface;

import java.io.File;
import java.util.List;

public class CreateTemplateModelImpl implements com.app.model.interfaces.CreateTemplateModel {

    @Override
    /*
     * 
     */
    public void runRawQuery(CreateTemplateResultInterface templateResultInterface, String query) {
        // Add implementation for the query here
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
        // Add implementation for the file over here
    	List<String> sheetNames = ParseUtils.getSheetNames(fileName);
    	// which sheet do you want to look at? ask user
    	// sheetX 2 for now [2-9]
    	int sheetIndex = 2;
    	String templateName = ParseUtils.getTemplateNameExcel(fileName, sheetIndex);
    	List<String> columnIds = ParseUtils.columnsIdsExcel(fileName, sheetIndex);
    	List<String> columnNames = ParseUtils.getColumnsNamesExcel(fileName, sheetIndex);
    	int numOfColumns = columnIds.size();
    	// columnName and Id mapping, store in table 'VariableName'
    	for (int i = 0; i<numOfColumns; i++ ) {
    		boolean res = QueryOnDatabase.insertVariableName(columnIds.get(i), columnNames.get(i));
//    		if (!res) {
//    			throw new InsertionFailedException();
//    		}
    	}
    	String tableName = sheetNames.get(sheetIndex);
    	// create an empty table with given table Name and insert the columns
    	if (QueryOnDatabase.createTable(tableName)) {
    		for (int i = 0; i < numOfColumns; i++) {
        		
        	}
    	}
    	
        // Look at templateResultInterface for communication back with the presenter
    }
}
