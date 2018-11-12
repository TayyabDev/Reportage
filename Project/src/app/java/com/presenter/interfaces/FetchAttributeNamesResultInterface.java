package app.java.com.presenter.interfaces;

import app.java.com.model.Exceptions.SelectException;

import java.util.HashMap;
import java.util.List;

public interface  FetchAttributeNamesResultInterface {


    void onSuccessFetchingAttributes(List<String> attributes) throws SelectException;

    String onErrorFetchingAttributes(String errorMessage);
}

