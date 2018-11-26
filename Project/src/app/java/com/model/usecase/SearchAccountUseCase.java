package app.java.com.model.usecase;

import app.java.com.model.database.api.Command;
import app.java.com.model.database.api.SelectCommand;
import app.java.com.presenter.interfaces.SearchAccountResultInterface;

public class SearchAccountUseCase extends UseCase {
	private String query;
	private SearchAccountResultInterface resultInterface;

	public SearchAccountUseCase(SearchAccountResultInterface resultInterface, String query) {
		this.query = query;
		this.resultInterface = resultInterface;
	}

	@SuppressWarnings("static-access")
	@Override
	public void run() {
		String report = null;
		String[] check = null;
		Command cmd = new SelectCommand();

		try {
			report = cmd.runExecuteQuery(query);
			check = report.split("\n");
		} catch (Exception e) {
			resultInterface.onErrorSearchAccount("failed when ran " + e.getMessage());
		}

		if (check.length != 1) {
			resultInterface.onSuccessSearchAccount("success");
		} else {
			resultInterface.onErrorSearchAccount("Account was not found");
		}
		resultInterface.report(report);
	}
}
