package app.java.com.presenter.interfaces;

import app.java.com.view.interfaces.CustomReportView;

import java.util.List;

public interface CustomReportPresenter {
    void createReport(List<String> attributes, String date1, String date2);

    void attachView(CustomReportView view);

    void unbindView();

    void fetchTemplatesAndAttributes();
}
