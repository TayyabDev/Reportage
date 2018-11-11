package app.java.com.model.usecase;

import app.java.com.model.Exceptions.SelectException;
import app.java.com.model.database.api.SelectCommand;
import app.java.com.presenter.interfaces.FetchTemplateNamesResultInterface;
import java.util.ArrayList;
import java.util.List;

public class FetchTemplateNamesUseCase extends UseCase {

    public final String tableName = "Template";
    public final List<String> columnName = new ArrayList<String>();
    public FetchTemplateNamesResultInterface resultInterface;

    public FetchTemplateNamesUseCase(FetchTemplateNamesResultInterface resultInterface) {
    	this.resultInterface = resultInterface;
    }

    @Override
    public void run() {
		try {
			List<String> templateNames = fetchTemplateNames();
			resultInterface.onSuccessFetchingNames(templateNames);
		} catch (SelectException e) {
			resultInterface.onErrorFetchingNames("Error occurs when fetching template names.");
		}
    }

	public List<String> fetchTemplateNames() throws SelectException {
		columnName.add("templateName");
		//Fetch all Template Names using the SelectCommand
		SelectCommand command = new SelectCommand(columnName, tableName);
		List<List<String>> templateNames = command.selectHandle();
		List<String> formulatedTemplateNames = new ArrayList<>();
		for (List<String> template : templateNames) {
 			formulatedTemplateNames.add(template.get(0));
		}

		return formulatedTemplateNames;
	}
}
