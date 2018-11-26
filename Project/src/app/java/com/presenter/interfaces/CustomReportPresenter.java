package app.java.com.presenter.interfaces;

import java.util.List;

import app.java.com.view.interfaces.CustomReportView;

public interface CustomReportPresenter {
	void createReport(List<String> attributes, String date1, String date2);

	void attachView(CustomReportView view);

	void unbindView();

	void fetchTemplatesAndAttributes();
}
