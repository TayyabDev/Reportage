package app.java.com.presenter.interfaces;

import app.java.com.view.interfaces.CreateReportView;

public interface CreateReportPresenter {
    void createReport(String query);

    void attachView(CreateReportView view);

    void unbindView();
}
