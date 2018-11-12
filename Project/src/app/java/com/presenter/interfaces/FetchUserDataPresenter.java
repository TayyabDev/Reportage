package app.java.com.presenter.interfaces;

import java.util.List;

import app.java.com.view.interfaces.CreateUserDataView;

public interface FetchUserDataPresenter {
	
	void fetchUserDataWithSelection(List<String> target, String tableName, List<String> constraint);
	
	void fetchTemplateNames();
	
    void attachView(CreateUserDataView view);

    void unbindView();
    
}
