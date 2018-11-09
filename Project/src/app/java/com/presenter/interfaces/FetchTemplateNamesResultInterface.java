package app.java.com.presenter.interfaces;

import java.lang.reflect.Method;
import java.util.List;

import app.java.com.model.Exceptions.SelectException;

public interface FetchTemplateNamesResultInterface {

    void onSuccessFetchingNames(List<String> names) throws SelectException;

    String onErrorFetchingNames(String errorMessage);
}
