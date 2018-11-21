package app.java.com.model.usecase;

import app.java.com.model.database.api.Command;
import app.java.com.model.database.api.SelectCommand;
import app.java.com.presenter.interfaces.CreateReportResultInterface;
import app.java.com.presenter.interfaces.SearchAccountResultInterface;

public class SearchAccountUseCase extends UseCase{
    private String query;
    private SearchAccountResultInterface resultInterface;

	public SearchAccountUseCase(SearchAccountResultInterface resultInterface, String query) {
        this.query = query;
        this.resultInterface = resultInterface;
    }
	
	@Override
	public void run() {
		String report = null;
		Command cmd = new SelectCommand();
		
        try {
            report = cmd.runExecuteQuery(query);
        } catch (Exception e) {
            resultInterface.onErrorSearchAccount("failed when ran " + e.getMessage());
        }

        if (report != null) {
            resultInterface.onSuccessSearchAccount("success");
            resultInterface.report(report);
        } else {
        	resultInterface.onErrorSearchAccount("Account was not found");
        }
	}
}
