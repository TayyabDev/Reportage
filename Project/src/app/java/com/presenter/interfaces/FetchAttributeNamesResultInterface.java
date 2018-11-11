package app.java.com.presenter.interfaces;

import java.util.HashMap;
import java.util.List;

public interface FetchAttributeNamesResultInterface {

	void onSuccessFetchAttributeNames(HashMap<String, List<String>> attributes);
	
	String onErrorFetchAttributeNames(String failedMessage);
}
