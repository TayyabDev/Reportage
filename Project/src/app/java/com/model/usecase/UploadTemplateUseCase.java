package app.java.com.model.usecase;

import app.java.com.model.utilities.ExcelFile;
import app.java.com.presenter.interfaces.UploadTemplateResultInterface;

public class UploadTemplateUseCase extends UseCase {

    private String templateFileName;
    private UploadTemplateResultInterface resultInterface;
    private static final int SHEET_NUMBER = 0;

    public UploadTemplateUseCase(UploadTemplateResultInterface resultInterface, String templateFileName) {
        this.resultInterface = resultInterface;
        this.templateFileName = templateFileName;
    }

    @Override
    public void run() {

        String formulatedFileName = templateFileName.replace("\\", "\\\\");
        System.out.println(formulatedFileName);
        ExcelFile exc = new ExcelFile(formulatedFileName);

        // Verify template matches the chosen template's format
        int numColumns = exc.getSheetNumColumns(SHEET_NUMBER);



        // Upload the template into the database using insert command

        // Notify the presenter


    }
}
