package app.java.com.presenter.interfaces;

import java.util.List;

public interface FetchUserDataResultInterface {
	
	void onSuccessSelectTable(List<String> columns, List<List<String>> data);

	void onErrorSelectTable(String message);
	
	void onSuccessUpdate(String message);

	void onErrorUpdate(String message);

}
