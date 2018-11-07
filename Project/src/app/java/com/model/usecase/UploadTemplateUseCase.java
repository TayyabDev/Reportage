package app.java.com.model.usecase;

import app.java.com.model.Exceptions.SelectException;
import app.java.com.model.database.api.Command;
import app.java.com.model.database.api.InsertCommand;
import app.java.com.model.database.api.SelectCommand;
import app.java.com.model.utilities.FileTypeFinder;
import app.java.com.model.utilities.templateFile.TemplateFileCsvImpl;
import app.java.com.model.utilities.templateFile.TemplateFileExcelImpl;
import app.java.com.model.utilities.templateFile.TemplateFileInterface;
import app.java.com.presenter.interfaces.UploadTemplateResultInterface;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UploadTemplateUseCase extends UseCase {

    private String templateFilePath;
    private String templateName;
    private UploadTemplateResultInterface resultInterface;
    private Date dateSelected;
    private static final int SHEET_NUMBER = 2;

    public UploadTemplateUseCase(UploadTemplateResultInterface resultInterface, Date date, String templateName,
                                 String templateFilePath) {
        this.resultInterface = resultInterface;
        this.dateSelected = date;
        this.templateFilePath = templateFilePath;
        this.templateName = templateName;
    }

    @Override
    public void run() {

        // Get the clientFormId after uploading it to the database
        int clientFormId = insertClientDataForm(dateSelected, templateName);


        // Now add this to the insert command since the template has a clientIdForm column





        String formulatedFileName = templateFilePath.replace("\\", "\\\\");
        System.out.println(formulatedFileName);
        TemplateFileInterface exc;
        if (FileTypeFinder.isCSVFile(formulatedFileName)){
        	exc = new TemplateFileCsvImpl(formulatedFileName);
        } else {
        	exc = new TemplateFileExcelImpl(formulatedFileName, SHEET_NUMBER);
        }

        // Verify template matches the chosen template's format
        List<Exception> errorList = insertAllRows(exc);

        // all rows inserted successfully if errorList is empty
        if (!errorList.isEmpty()) {
        	List<String> errorMessages = new ArrayList<>();
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
        List<Exception> errorList = new ArrayList<>();
        System.out.println("no error in insertAllRows so far");
        for (int i = 0; i < numOfRow; i++){
        	row = exc.getRow(i+3);
        	Command insert = new InsertCommand(templateName,columnIds, row);
        	try {
				insert.handle();
			} catch (Exception e) {
				errorList.add(e);
				e.printStackTrace();
			}
        }
        return errorList;
    }

    private int insertClientDataForm(Date date, String templateSelected) {

        // Get the templateId using the templateSelected

        List<String> target = new ArrayList<>();
        target.add("templateId");

        List<String> constraints = new ArrayList<>();
        constraints.add("`tableName`= \'" + templateSelected + "\'");

        SelectCommand selectTemplateId = new SelectCommand(target, "Template", constraints);
        List<List<String>> result = null;
        try {
            result = selectTemplateId.selectHandle();
        } catch (SelectException e) {
            e.printStackTrace();
        }
        System.out.println("Hey what is here");
        System.out.println(result);

        int monthSelected = date.getMonth();
        int year = date.getYear();

        return 0;

    }
}
