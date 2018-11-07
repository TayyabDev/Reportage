package app.java.com.presenter.interfaces;

import java.util.List;

import app.java.com.view.interfaces.CreateUserDataView;

public interface FetchUserDataPresenter {
	
	public void fetchUserDataWithSelection(List<String> target, String tableName, List<String> constraint);
	
    void attachView(CreateUserDataView view);

    void unbindView();
    
}
