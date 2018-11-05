package app.java.com.model.usecase;

import app.java.com.model.Exceptions.SelectException;
import app.java.com.model.database.api.SelectCommand;
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

        String constraint = "templateName = \'" + templateName + "\'";
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

        try {
            System.out.println(" The size is " + command.getColumnIds().size());
        } catch (SelectException e) {
            System.out.println("Error here!");
            e.printStackTrace();
        }


        // Get the file from the file path






    }
}
