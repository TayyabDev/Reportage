package app.java.com.presenter.interfaces;

import app.java.com.view.interfaces.AddNewReportFormatView;

public interface AddNewReportFormatPresenter {

	void addNewFormat(String reportName, String sql);

	void attachView(AddNewReportFormatView view);

	void unbindView();
}
