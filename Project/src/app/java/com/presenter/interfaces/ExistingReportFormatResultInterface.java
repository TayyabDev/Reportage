package app.java.com.presenter.interfaces;

import java.util.List;

import app.java.com.view.interfaces.ExistingReportFormatView;

public interface ExistingReportFormatResultInterface {

	void onSuccessCreatingReport();

    void onErrorCreatingReport(List<String> errorReportNames);
    
}
