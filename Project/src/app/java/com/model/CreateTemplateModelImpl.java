package app.java.com.model;


import app.java.com.model.Exceptions.AlterException;
import app.java.com.model.Exceptions.CreateException;
import app.java.com.model.database.api.QueryOnDatabase;
import app.java.com.model.entities.template.Template;
import app.java.com.model.interfaces.CreateTemplateModel;
import app.java.com.model.utilities.templateFile.TemplateFileCsvImpl;
import app.java.com.model.utilities.templateFile.TemplateFileExcelImpl;
import app.java.com.model.utilities.templateFile.TemplateFileInterface;
import app.java.com.presenter.interfaces.CreateTemplateResultInterface;

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
    		// TODO: make this a parameter of the function and get from UI.
    		int sheetNum = 0;

    		TemplateFileInterface templateParam = null;
    		if (formulatedFileName.substring(formulatedFileName.length()-4).equals("xlsx")) {
    			sheetNum = 2;
    			templateParam = new TemplateFileExcelImpl(formulatedFileName, sheetNum);
    		}
    		else if (formulatedFileName.substring(formulatedFileName.length()-3).equals("csv")) {
    			templateParam = new TemplateFileCsvImpl(formulatedFileName);
    		}
    		Template template = templateParam.getFileAsTemplate();

        boolean success = false;
        
		try {
			success = QueryOnDatabase.createTemplate(template.getTemplateName(),
					template.getTableName(), template.getColumnIds(), template.getColumnNames());
		} catch (AlterException | CreateException e) {
			templateResultInterface.onErrorCreateTemplate(e.getMessage());
		}
        if (success) {
        	templateResultInterface.onSuccessCreateTemplate();
        } 
        
        // Look at templateResultInterface for communication back with the presenter
    }
}
