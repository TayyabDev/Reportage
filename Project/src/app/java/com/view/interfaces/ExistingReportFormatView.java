package app.java.com.view.interfaces;

import java.util.HashMap;
import java.util.List;

public interface ExistingReportFormatView {

	void onSuccessCreateReport();
	void onErrorCreateReport(List<String> failedReportNames);
	void displayExistingReport(HashMap<String,String> nameQuery);
	
	void errorFetchReportFormats(String message);
	void errorFetchReportFormats();
}
