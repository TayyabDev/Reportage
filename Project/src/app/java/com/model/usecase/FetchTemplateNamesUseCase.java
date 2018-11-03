package app.java.com.model.usecase;

import app.java.com.model.database.api.Command;
import app.java.com.model.database.api.SelectCommand;

public class FetchTemplateNamesUseCase extends UseCase {

    public final String tableName = "Template";
    public final String columnName = "templateName";

    public FetchTemplateNamesUseCase() {

    }

    @Override
    public void run() {
        // Fetch all Template Names using the SelectCommand
        //Command command = new SelectCommand(tableName, columnName);
        //command.handle();
    }
}
