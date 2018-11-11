package app.java.com.presenter.interfaces;

import java.util.List;

public interface FetchUserDataResultInterface {

	void onSuccessSelectTable(List<List<String>> data);
	
	void onErrorSelectTable(String message);

}
