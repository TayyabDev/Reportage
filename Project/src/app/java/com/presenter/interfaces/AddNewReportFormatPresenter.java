package app.java.com.presenter.interfaces;

import app.java.com.view.interfaces.AddNewReportFormatView;
import app.java.com.view.interfaces.CreateAccountView;
import app.java.com.view.interfaces.CreateReportView;

public interface AddNewReportFormatPresenter {

	void addNewFormat(String reportName, String sql);

    void attachView(AddNewReportFormatView view);

    void unbindView();
}
