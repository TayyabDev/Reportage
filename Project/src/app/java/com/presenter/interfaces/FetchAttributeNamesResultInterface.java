package app.java.com.presenter.interfaces;

import java.util.List;

import app.java.com.model.Exceptions.SelectException;

public interface FetchAttributeNamesResultInterface {


	void onSuccessFetchingAttributes(List<String> attributes) throws SelectException;

	String onErrorFetchingAttributes(String errorMessage);
}

