package app.java.com.model.usecase;

import java.util.ArrayList;
import java.util.List;

import app.java.com.model.database.api.Command;
import app.java.com.model.database.api.InsertCommand;
import app.java.com.model.utilities.ExcelFile;
import app.java.com.model.utilities.templateFile.TemplateFileExcelImpl;
import app.java.com.model.utilities.templateFile.TemplateFileInterface;
import app.java.com.presenter.interfaces.UploadTemplateResultInterface;

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
        TemplateFileInterface exc = new TemplateFileExcelImpl(formulatedFileName, 2);
        
//        ExcelFile exc = new ExcelFile(formulatedFileName);

        // Verify template matches the chosen template's format
//        int numColumns = exc.getSheetNumColumns(SHEET_NUMBER);



        // Upload the template into the database using insert command
        List<String> columnIds = exc.getColumnIds();
        List<String> row = null;
        // get the tableName in the database
        String templateName = exc.getTableName();
        int numOfRow = exc.getNumRows();
        System.out.println(numOfRow);
        List<Integer> errorList = new ArrayList<Integer>();
        int i;
        for (i = 0; i < numOfRow; i++){
        	row = exc.getRow(i+3);
        	Command insert = new InsertCommand(templateName,columnIds, row);
        	try {
				insert.handle();
			} catch (Exception e) {
				errorList.add(i);
			}
        }
        // Notify the presenter


    }
}
