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
    private static final String CLIENTDATAFORMCOLUMN = "clientDataFormId";
    private static final int NORMALIZE_YEAR = 1900;
    private static final int NORMALIZE_MONTH = 1;

    public UploadTemplateUseCase(UploadTemplateResultInterface resultInterface, Date date, String templateName,
                                 String templateFilePath) {
        this.resultInterface = resultInterface;
        this.dateSelected = date;
        this.templateFilePath = templateFilePath.replace("\\", "\\\\");
        this.templateName = templateName;
    }

    @Override
    public void run() {

        TemplateFileInterface exc;
    	List<String> errorMessages = new ArrayList<>();

        // Get the clientFormId after uploading it to the database
        int clientFormId = insertClientDataForm(dateSelected, templateName);
        
        if(clientFormId == -1) {
        	errorMessages.add("Invalid ClientDataFormId");
        	resultInterface.onErrorUploadingTemplate(errorMessages);
        }
        
        if (FileTypeFinder.isCSVFile(templateFilePath)){
        	exc = new TemplateFileCsvImpl(templateFilePath);
        } else {
        	exc = new TemplateFileExcelImpl(templateFilePath, SHEET_NUMBER);
        }

        // Verify template matches the chosen template's format
        List<Exception> errorList = insertAllRows(exc, clientFormId);

        // all rows inserted successfully if errorList is empty
        if (!errorList.isEmpty()) {
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
    private List<Exception> insertAllRows(TemplateFileInterface file, int clientDataFormId) {
    	// Upload the template into the database using insert command
        List<String> columnIds = file.getColumnIds();
        columnIds.add(0, CLIENTDATAFORMCOLUMN);
        List<String> row = null;
        
        // get the tableName in the database
        String templateName = file.getTableName();
        int numOfRow = file.getNumRows();
        List<Exception> errorList = new ArrayList<>();

        for (int i = 0; i < numOfRow; i++){
        	row = file.getRow(i+3);
        	row.add(0, String.valueOf(clientDataFormId));
        	Command insert = new InsertCommand(templateName,columnIds, row);
        	try {
				insert.handle();
			} catch (Exception e) {
				errorList.add(e);
			}
        }
        return errorList;
    }

    private int insertClientDataForm(Date date, String templateSelected) {

        // Get the templateId using the templateSelected

        List<String> target = new ArrayList<>();
        target.add("templateId");

        templateSelected = templateSelected.replace(' ', '_');
        templateSelected = '`' + templateSelected + '`';

        List<String> constraints = new ArrayList<>();
        String constraintTemplateName = "`tableName`= \'" + templateSelected + "\'";

        constraints.add(constraintTemplateName);

        SelectCommand selectTemplateId = new SelectCommand(target, "Template", constraints);
        List<List<String>> result = null;
        try {
            result = selectTemplateId.selectHandle();
        } catch (SelectException e) {
        	return -1;
        }

        String templateId = result.get(0).get(0);

        int month = dateSelected.getMonth() + NORMALIZE_MONTH; // normalize

        int year = dateSelected.getYear() + NORMALIZE_YEAR; // normalize

        // Let's insert the information into the ClientDataForm table now
        // we only need to upload the templateId, month, and year
        String clientDataFormTableName = "ClientDataForm";
        List<String> attributes = new ArrayList<>();
        attributes.add("templateId");
        attributes.add("reviewerId"); // for now it is set to 1
        attributes.add("agencyId"); // for now it is set to 1
        attributes.add("month");
        attributes.add("year");

        List<String> values = new ArrayList<>();
        values.add(templateId);
        values.add(String.valueOf(1));
        values.add(String.valueOf(1));
        values.add(String.valueOf(month));
        values.add(String.valueOf(year));

        InsertCommand insertCommand = new InsertCommand(clientDataFormTableName, attributes, values);

        try {
            return insertCommand.insertHandle();
        } catch (Exception e) {
            return -1;
        }
        
    }
}
