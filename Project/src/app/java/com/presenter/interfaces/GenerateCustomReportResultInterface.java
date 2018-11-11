package app.java.com.presenter.interfaces;

import java.util.HashMap;
import java.util.List;

public interface GenerateCustomReportResultInterface {

	void onSuccessGenerateCustomReport(HashMap<String, List<List<String>>> result);
	
	void onErrorGenerateCustomReport(String errorMessage);
}
