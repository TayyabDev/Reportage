package app.java.com.model.usecase;

import app.java.com.model.database.api.Command;
import app.java.com.model.database.api.SelectCommand;
import app.java.com.presenter.interfaces.CreateReportResultInterface;

public class CreateReportWithQueryUseCase implements UseCase {
	private String query;
	private CreateReportResultInterface resultInterface;

	public CreateReportWithQueryUseCase(CreateReportResultInterface resultInterface, String query) {
		this.query = query;
		this.resultInterface = resultInterface;
	}

	@SuppressWarnings("static-access")
	@Override
	public void run() {
		String report = null;
		Command cmd = new SelectCommand();

		try {
			report = cmd.runExecuteQuery(query);
		} catch (Exception e) {
			resultInterface.onErrorCreateReport("failed when ran " + e.getMessage());
		}

		if (report != null) {
			resultInterface.onSuccessCreateReport("success");
			resultInterface.report(report);
		}
	}

}
