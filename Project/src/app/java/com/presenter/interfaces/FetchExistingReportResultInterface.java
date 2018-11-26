package app.java.com.presenter.interfaces;

import java.util.HashMap;

public interface FetchExistingReportResultInterface {

	void onSuccessFetchExistingReport(HashMap<String, String> map);

	void onErrorFetchExistingReport(String errorMessage);
}
