package app.java.com.view.interfaces;

import java.util.HashMap;
import java.util.List;

public interface CustomReportView {
    void onSuccessReportCreated();


    void onErrorCreatingReport();

    boolean isFieldsValid(String date1, String date2);

    void invalidFields();

    void sendReport(String report);

    void fetchAttributes(List<String> attrs);

    void errorFetchingAttributes();
}
