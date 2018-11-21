package app.java.com.model.usecase;

import app.java.com.model.Exceptions.SelectException;
import app.java.com.model.database.api.SelectCommand;
import app.java.com.model.utilities.templateFile.TemplateFileInterface;
import app.java.com.presenter.interfaces.VerifyTemplateResultInterface;
import java.util.ArrayList;
import java.util.List;

public class VerifyTemplateUseCase extends UseCase {

    private VerifyTemplateResultInterface resultInterface;
    private TemplateFileInterface file;
    private String templateName;

    public VerifyTemplateUseCase(VerifyTemplateResultInterface resultInterface, TemplateFileInterface fileInterface, String templateName) {
        this.resultInterface = resultInterface;
        this.file = fileInterface;
        this.templateName = templateName;
    }

    @Override
    public void run() {
        List<String> columnList = new ArrayList<>();
        columnList.add("tableName");

        List<String> constraints = new ArrayList<>();
        constraints.add("templateName = \'" + templateName + "\'");

        // Get the table name based on template name given
        SelectCommand tableNameCommand  = new SelectCommand(columnList, "`Template`", constraints);
        System.out.println();
        List<List<String> > resultSet = null;
        try {
            resultSet = tableNameCommand.selectHandle();
        } catch (SelectException e) {
            System.out.println("Error when selecting: " + columnList.toString() + "`Template`" + constraints.toString());
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

        List<String> selectedFileColumns = file.getColumnIds();

        int adjustedTemplateColumns = selectedTemplateColumns.size() - 2;
        int templateColumnIndex = 2;

        if(adjustedTemplateColumns != selectedFileColumns.size()) {
            this.resultInterface.onTemplateSelectedCompatible(false, file);
            return;
        }

        for(int columnIdIndex = 0; columnIdIndex < selectedFileColumns.size(); columnIdIndex++) {
            if(!selectedFileColumns.get(columnIdIndex).equals(selectedTemplateColumns.get(templateColumnIndex))) {
                this.resultInterface.onTemplateSelectedCompatible(false, file);
                return;
            }

            templateColumnIndex++;
        }

        // If manage to get here, then send template valid result
        this.resultInterface.onTemplateSelectedCompatible(true, file);
    }
}
