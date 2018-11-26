package app.java.com.presenter.interfaces;

import java.util.List;

import app.java.com.model.entities.DataChanges;
import app.java.com.view.interfaces.CreateUserDataView;

public interface FetchUserDataPresenter {

	void fetchUserDataWithSelection(String tableName);

	void fetchTemplateNames();

	void submitChanges(String templateName, List<List<String>> orignal, List<List<String>> changes);

	void updateChanges(List<DataChanges> changesList, String tableName);

	void attachView(CreateUserDataView view);

	void unbindView();

}
