package app.java.com.presenter.interfaces;

import java.util.HashMap;
import java.util.List;

public interface CustomReportResultInterface {

    void onSuccessCreatingReport();

    void onErrorCreatingReport();

    void sendReport(HashMap<String, List<List<String>>> data);

}
