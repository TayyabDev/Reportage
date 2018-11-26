package app.java.com.presenter.interfaces;

import java.util.List;

import app.java.com.model.entities.DataChanges;

public interface UpdateUserDataResultInterface {

	void onSuccessSelectTable(List<String> columns, List<List<String>> data);

	void onErrorSelectTable(String message);

	void onShowDataChanges(List<DataChanges> changesList);

	void onSuccessUpdate(String message);

	void onErrorUpdate(String message);
}
