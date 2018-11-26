package app.java.com.view.interfaces;

import java.util.List;

public interface TrendReportView {

    //void displayImage();

    void createCSVFile(List<List<String>> data);

    void displayError(String message);

}
