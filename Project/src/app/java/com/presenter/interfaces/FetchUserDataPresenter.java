package app.java.com.presenter.interfaces;

import java.util.List;

import app.java.com.view.interfaces.ViewUserDataView;

public interface FetchUserDataPresenter {

	public void fetchUserDataWithQuery(String query);

	public void fetchUserDataWithSelection(List<String> selection);
	
    void attachView(ViewUserDataView view);

    void unbindView();
    
}
