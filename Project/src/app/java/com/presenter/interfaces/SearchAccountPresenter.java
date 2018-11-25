package app.java.com.presenter.interfaces;

import app.java.com.view.interfaces.SearchAccountView;

public interface SearchAccountPresenter {
	void searchAccount(String query);

	void attachView(SearchAccountView view);

	void unbindView();
}
