package app.java.com.presenter;

import java.io.FileNotFoundException;

import app.java.com.model.usecase.CreateReportWithQueryUseCase;
import app.java.com.model.usecase.SearchAccountUseCase;
import app.java.com.presenter.interfaces.SearchAccountPresenter;
import app.java.com.presenter.interfaces.SearchAccountResultInterface;
import app.java.com.view.interfaces.SearchAccountView;

public class SearchAccountPresenterImpl implements SearchAccountResultInterface, SearchAccountPresenter{

	private SearchAccountView view;
	
	@Override
	public void searchAccount(String query) {
        if(view.isFieldsValid(query)){
            SearchAccountUseCase useCase = new SearchAccountUseCase(this, query);
            useCase.run();
        } else {
            // should make new method telling user fields are invalid
            view.invalidFields();
        }
	}

	@Override
	public void attachView(SearchAccountView view) {
		this.view = view;
		
	}

	@Override
	public void unbindView() {
		this.view = null;
		
	}

	@Override
	public void onSuccessSearchAccount(String meesage) {
		view.onSuccessSearchAccount();
		
	}

	@Override
	public void onErrorSearchAccount(String message) {
		view.onErrorSearchAccount();
		
	}

	@Override
	public void report(String report) {
		view.displayData(report);
	}

}
