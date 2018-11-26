package app.java.com.presenter.interfaces;

import java.util.HashMap;

import app.java.com.view.interfaces.ExistingReportFormatView;

public interface ExistingReportFormatPresenter {

	void fetchExistingReports();

	void generateReport(HashMap<String, String> querys);

	void attachView(ExistingReportFormatView view);

	void unbindView();
}
