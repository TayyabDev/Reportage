package app.java.com.presenter.interfaces;

import java.util.List;

public interface ExistingReportFormatResultInterface {

	void onSuccessCreatingReport();

	void onErrorCreatingReport(List<String> errorReportNames);

}
