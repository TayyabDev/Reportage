package app.java.com.model.usecase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import app.java.com.model.Exceptions.SelectException;
import app.java.com.model.database.api.SelectCommand;
import app.java.com.presenter.interfaces.FetchExistingReportResultInterface;

public class FetchExistingReportUseCase implements UseCase {

	private FetchExistingReportResultInterface resultInterface;

	private final String reportTable = "Report";
	private final String reportNameCol = "reportName";
	private final String reportQueryCol = "reportQuery";

	public FetchExistingReportUseCase(FetchExistingReportResultInterface resultInterface) {
		this.resultInterface = resultInterface;
	}

	@Override
	public void run() {
		try {
			HashMap<String, String> reports = fetchExistingReport();
			resultInterface.onSuccessFetchExistingReport(reports);

		} catch (SelectException e) {
			resultInterface.onErrorFetchExistingReport(e.getMessage());
		}

	}

	/*
	 * get the reportName: query in database
	 */
	private HashMap<String, String> fetchExistingReport() throws SelectException {
		List<String> attrs = new ArrayList<String>();
		attrs.add(reportNameCol);
		attrs.add(reportQueryCol);
		SelectCommand fetchReport = new SelectCommand(attrs, reportTable);
		List<List<String>> nameQueryList = fetchReport.selectHandle();

		HashMap<String, String> nameQueryMap = new HashMap<String, String>();
		for (List<String> nameQuery : nameQueryList) {
			String name = nameQuery.get(0);
			String query = nameQuery.get(1);
			nameQueryMap.put(name, query);
		}
		return nameQueryMap;
	}

}
