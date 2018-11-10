package app.java.com.view.interfaces;

public interface CreateReportView {
    void onSuccessReportCreated();

    void onErrorReportCreated();

    boolean isFieldsValid(String query);

    void invalidFields();
    
    void sendReport(String report);
}
