package app.java.com.presenter.interfaces;

public interface AddNewReportFormatResultInterface {

	void onSuccessAddReportFormat(String csvString);

    void onErrorAddReportFormat(String message);
    
    void onErrorAddReportFormat();
}
