package app.java.com.model;

import app.java.com.model.Exceptions.AlterException;
import app.java.com.model.Exceptions.CreateException;
import app.java.com.model.database.api.QueryOnDatabase;
import app.java.com.model.entities.template.Template;
import app.java.com.model.interfaces.CreateTemplateModel;
import app.java.com.model.interfaces.CreateTemplateResultInterface;
import app.java.com.model.utilities.createTemplate.CreateTemplateParamCSVImpl;
import app.java.com.model.utilities.createTemplate.CreateTemplateParamExcelImpl;
import app.java.com.model.utilities.createTemplate.CreateTemplateParamInterface;

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
    		// TODO: make this a parameter of the function and get from UI. 
    		int sheetNum = 0;
    		
    		CreateTemplateParamInterface templateParam = null;
    		if (formulatedFileName.substring(formulatedFileName.length()-4).equals("xlsx")) {
    			templateParam = new CreateTemplateParamExcelImpl();
    			sheetNum = 2;
    		}
    		else if (formulatedFileName.substring(formulatedFileName.length()-3).equals("csv")) {
    			templateParam = new CreateTemplateParamCSVImpl();
    			sheetNum = -1;
    		}
    		Template template = templateParam.getTemplateNameColumns(formulatedFileName, sheetNum);

        boolean success = false;
        
		try {
			success = QueryOnDatabase.createTemplate(template.getTemplateName(),
					template.getTableName(), template.getColumnIds(), template.getColumnNames());
		} catch (AlterException | CreateException e) {
			templateResultInterface.onErrorCreateTemplate(e.getMessage());
		}
        if (success) {
        	templateResultInterface.onSuccessCreateTemplate("success");
        } 
        
        // Look at templateResultInterface for communication back with the presenter
    }
}
