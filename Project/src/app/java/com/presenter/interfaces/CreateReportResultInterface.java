package app.java.com.presenter.interfaces;

public interface CreateReportResultInterface {

    void onSuccessCreateReport(String message);

    void onErrorCreateReport(String message);
    
    void report(String report);

}
