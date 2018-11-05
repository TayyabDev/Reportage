package app.java.com.model.usecase;

import app.java.com.model.database.api.Command;
import app.java.com.model.database.api.InsertCommand;
import app.java.com.model.utilities.FileTypeFinder;
import app.java.com.model.utilities.templateFile.TemplateFileCsvImpl;
import app.java.com.model.utilities.templateFile.TemplateFileExcelImpl;
import app.java.com.model.utilities.templateFile.TemplateFileInterface;
import app.java.com.presenter.interfaces.UploadTemplateResultInterface;
import java.util.ArrayList;
import java.util.List;

public class UploadTemplateUseCase extends UseCase {

    private String templateFilePath;
    private UploadTemplateResultInterface resultInterface;
    private static final int SHEET_NUMBER = 0;

    public UploadTemplateUseCase(UploadTemplateResultInterface resultInterface, String templateFilePath) {
        this.resultInterface = resultInterface;
        this.templateFilePath = templateFilePath;
    }

    @Override
    public void run() {

        String formulatedFileName = templateFilePath.replace("\\", "\\\\");
        System.out.println(formulatedFileName);
        TemplateFileInterface exc;
        if (FileTypeFinder.isCSVFile(formulatedFileName)){
        	exc = new TemplateFileCsvImpl(formulatedFileName);
        } else {
        	exc = new TemplateFileExcelImpl(formulatedFileName, 2);
        }

        // Verify template matches the chosen template's format
        List<Exception> errorList = insertAllRows(exc);
        // all rows inserted successfully if errorList is empty
        if (!errorList.isEmpty()) {
        	List<String> errorMessages = new ArrayList<String>();
        	for (Exception e : errorList) {
        		errorMessages.add(e.getMessage());
        	}
        	resultInterface.onErrorUploadingTemplate(errorMessages);
        } else {
        	resultInterface.onSuccessUploadingTemplate();
        }
        
    }

    /*
     * inserting all the data rows in the TemplateFileInterface into the database
     */
    private List<Exception> insertAllRows(TemplateFileInterface exc) {
    	// Upload the template into the database using insert command
        List<String> columnIds = exc.getColumnIds();
        List<String> row = null;
        // get the tableName in the database
        String templateName = exc.getTableName();
        int numOfRow = exc.getNumRows();
        List<Exception> errorList = new ArrayList<Exception>();
        int i;
        for (i = 0; i < numOfRow; i++){
        	row = exc.getRow(i+3);
        	Command insert = new InsertCommand(templateName,columnIds, row);
        	try {
				insert.handle();
			} catch (Exception e) {
				errorList.add(e);
			}
        }
        return errorList;
    }
}
