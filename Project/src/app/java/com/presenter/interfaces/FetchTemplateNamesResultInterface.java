package app.java.com.presenter.interfaces;

import java.util.List;

public interface FetchTemplateNamesResultInterface {

    List<String> onSuccessFetchingNames();

    String onErrorFetchingNames();
}
