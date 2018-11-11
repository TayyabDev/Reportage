package app.java.com.model.usecase;

import app.java.com.model.Exceptions.SelectException;
import app.java.com.model.database.api.SelectCommand;
import app.java.com.model.utilities.FileTypeFinder;
import app.java.com.model.utilities.templateFile.TemplateFileCsvImpl;
import app.java.com.model.utilities.templateFile.TemplateFileExcelImpl;
import app.java.com.model.utilities.templateFile.TemplateFileInterface;
import app.java.com.presenter.interfaces.VerifyTemplateResultInterface;
import java.util.ArrayList;
import java.util.List;

public class VerifyTemplateUseCase extends UseCase {

    private VerifyTemplateResultInterface resultInterface;
    private String filePath;
    private String templateName;

    public VerifyTemplateUseCase(VerifyTemplateResultInterface resultInterface, String filePath, String templateName) {
        this.resultInterface = resultInterface;
        this.filePath = filePath;
        this.templateName = templateName;
    }

    @Override
    public void run() {
        System.out.println("Hello here in Verify Template usecase");
        List<String> columnList = new ArrayList<>();
        columnList.add("tableName");

        List<String> constraint = new ArrayList<>();
        constraint.add("templateName = \'" + templateName + "\'");

        // Get the table name based on template name given
        SelectCommand tableNameCommand  = new SelectCommand(columnList, "`Template`", constraint);
        System.out.println();
        List<List<String> > resultSet = null;
        try {
            resultSet = tableNameCommand.selectHandle();
            System.out.println("The result set is " + resultSet);
        } catch (SelectException e) {
            System.out.println("Hey Error message");
        }

        SelectCommand command = new SelectCommand(resultSet.get(0).get(0));

        // Get the number of columns for the selected Template
        List<String> selectedTemplateColumns = null;

        try {
            selectedTemplateColumns = command.getColumnIds();
        } catch (SelectException e) {
            System.out.println("Error extracting number of columns of template!");
            e.printStackTrace();
        }

        // Get the file from the file path
        String formulatedFileName = filePath.replace("\\", "\\\\");
        System.out.println(formulatedFileName);

        TemplateFileInterface fileInterface = null;

        if(FileTypeFinder.isCSVFile(formulatedFileName)) {
            fileInterface = new TemplateFileCsvImpl(formulatedFileName);
        } else {
            fileInterface = new TemplateFileExcelImpl(formulatedFileName, 2);
        }

        List<String> selectedFileColumns = fileInterface.getColumnIds();

        if(selectedTemplateColumns.size() != selectedFileColumns.size()) {
            this.resultInterface.onTemplateSelectedCompatible(false);
            return;
        }

        for(int columnIdIndex = 0; columnIdIndex < selectedTemplateColumns.size(); columnIdIndex++) {
            if(!selectedFileColumns.get(columnIdIndex).equals(selectedTemplateColumns.get(columnIdIndex))) {
                this.resultInterface.onTemplateSelectedCompatible(false);
                return;
            }
        }

        // If manage to get here, then send template valid result
        this.resultInterface.onTemplateSelectedCompatible(true);
    }
}
