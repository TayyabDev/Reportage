package app.java.com.view.interfaces;

import java.io.FileNotFoundException;

public interface CreateReportView {
    void onSuccessReportCreated();

    void onErrorReportCreated();

    boolean isFieldsValid(String query);

    void invalidFields();
    
    String sendReport(String report) throws FileNotFoundException;
}
