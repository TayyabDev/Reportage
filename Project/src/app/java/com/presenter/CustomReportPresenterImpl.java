package app.java.com.presenter;

import app.java.com.model.Exceptions.SelectException;
import app.java.com.model.usecase.FetchAttributeNamesUseCase;
import app.java.com.model.usecase.UseCase;
import app.java.com.presenter.interfaces.CustomReportPresenter;
import app.java.com.presenter.interfaces.CustomReportResultInterface;
import app.java.com.presenter.interfaces.FetchAttributeNamesResultInterface;
import app.java.com.view.interfaces.CustomReportView;

import java.util.List;

public class CustomReportPresenterImpl implements CustomReportPresenter, CustomReportResultInterface, FetchAttributeNamesResultInterface {

    private CustomReportView view;

    @Override
    public void createReport(List<String> attributes, String date1, String date2) {

    }

    @Override
    public void attachView(CustomReportView view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        this.view = null;
    }

    @Override
    public void fetchTemplatesAndAttributes() {
        UseCase fetch = new FetchAttributeNamesUseCase(this);
        fetch.run();

    }

    @Override
    public void onSuccessCreatingReport() {
        view.onSuccessReportCreated();
    }

    @Override
    public void onErrorCreatingReport() {
        view.onErrorCreatingReport();
    }

    @Override
    public void onSuccessFetchingAttributes(List<String> attributes) throws SelectException {
        this.view.fetchAttributes(attributes);
    }

    @Override
    public String onErrorFetchingAttributes(String errorMessage) {

        this.view.errorFetchingAttributes();
        return errorMessage;
    }
}
