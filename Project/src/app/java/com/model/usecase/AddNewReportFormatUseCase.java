package app.java.com.model.usecase;

import java.util.ArrayList;
import java.util.List;

import app.java.com.model.Exceptions.DuplicateKeyException;
import app.java.com.model.database.api.Command;
import app.java.com.model.database.api.InsertCommand;
import app.java.com.presenter.interfaces.AddNewReportFormatResultInterface;

public class AddNewReportFormatUseCase extends UseCase {

	private String reportName;
	private String query;
	private AddNewReportFormatResultInterface resultInterface;
	// insert the query to database table Report table
	private final String reportTable = "Report";
	private final String reportNameCol = "reportName";
	private final String reportQueryCol = "reportQuery";
	
	public AddNewReportFormatUseCase(AddNewReportFormatResultInterface resultInterface, String reportName, String query) {
		this.query = query;
		this.resultInterface = resultInterface;
		this.reportName = reportName;
	}
	
	@Override
	public void run() {
		String res = "";
		try {
			res = checkQuery(query);
			saveQuery(reportName, query);
			resultInterface.onSuccessAddReportFormat(res);
		}catch (DuplicateKeyException e) {
			resultInterface.onErrorAddReportFormat(e.getMessage());
		}catch (Exception e) {
			resultInterface.onErrorAddReportFormat();
		}
	}
	
	private static String checkQuery(String query) throws Exception {
		return Command.runExecuteQuery(query);
	}
	
	private boolean saveQuery(String reportName, String query) throws DuplicateKeyException, Exception {
		List<String> attr = new ArrayList<>();
		List<String> val = new ArrayList<>();
		attr.add(reportNameCol);
		attr.add(reportQueryCol);
		val.add(reportName);
		val.add(query.replaceAll("'", "\\\\'"));
		Command saveQuery = new InsertCommand(reportTable, attr, val);
		return saveQuery.handle();
		
		
	}
}
