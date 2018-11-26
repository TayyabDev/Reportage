package app.java.com.model.usecase;

import java.util.ArrayList;
import java.util.List;

import app.java.com.model.Exceptions.SelectException;
import app.java.com.model.database.api.SelectCommand;
import app.java.com.presenter.interfaces.FetchTemplateNamesResultInterface;

public class FetchTemplateNamesUseCase implements UseCase {

	public final String tableName = "Template";
	public final List<String> columnName = new ArrayList<>();
	FetchTemplateNamesResultInterface resultInterface;



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
		// Fetch all Template Names using the SelectCommand
		SelectCommand command = new SelectCommand(columnName, tableName);
		List<List<String>> templateNames = command.selectHandle();
		List<String> formulatedTemplateNames = new ArrayList<>();

		for (List<String> template : templateNames) {
			formulatedTemplateNames.add(template.get(0));
		}

		return formulatedTemplateNames;
	}
}
