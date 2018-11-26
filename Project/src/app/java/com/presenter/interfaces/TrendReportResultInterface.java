package app.java.com.presenter.interfaces;

import java.util.List;

public interface TrendReportResultInterface {

   void onSuccessFetchingData(List<List<String>> data);

   void onErrorFetchingData(String message);





}
