package app.java.com.presenter;

import app.java.com.presenter.interfaces.TrendReportPresenter;
import app.java.com.presenter.interfaces.TrendReportResultInterface;
import app.java.com.view.interfaces.TrendReportView;

import java.util.List;

public class TrendReportPresenterImpl implements TrendReportPresenter, TrendReportResultInterface {


    private TrendReportView view;
    public TrendReportPresenterImpl(TrendReportView view){
        this.view =view;
    }

    @Override
    public void fetchData() {
        // run usecase here
    }


    @Override
    public void onSuccessFetchingData(List<List<String>> data) {
        view.createCSVFile(data);
    }

    @Override
    public void onErrorFetchingData(String message) {
            view.displayError(message);
    }
}
