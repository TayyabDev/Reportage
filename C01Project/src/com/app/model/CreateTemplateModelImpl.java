package com.app.model;

import com.app.model.database.api.QueryOnDatabase;
import com.app.model.interfaces.CreateTemplateResultInterface;

import java.io.File;

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
    	
        // Look at templateResultInterface for communication back with the presenter
    }
}
