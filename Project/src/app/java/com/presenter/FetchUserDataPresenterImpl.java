package app.java.com.presenter;

import java.util.List;

import app.java.com.model.Exceptions.SelectException;
import app.java.com.model.usecase.FetchTemplateNamesUseCase;
import app.java.com.model.usecase.FetchUserDataUseCase;
import app.java.com.presenter.interfaces.FetchTemplateNamesResultInterface;
import app.java.com.presenter.interfaces.FetchUserDataPresenter;
import app.java.com.presenter.interfaces.FetchUserDataResultInterface;
import app.java.com.view.interfaces.CreateUserDataView;

public class FetchUserDataPresenterImpl
		implements FetchUserDataPresenter, FetchUserDataResultInterface, FetchTemplateNamesResultInterface {

	private CreateUserDataView view;
	
	public FetchUserDataPresenterImpl(CreateUserDataView view) {
		this.view = view;
	} 
	
	@Override
	public void fetchUserDataWithSelection(List<String> target, String tableName, List<String> constraint) {
		FetchUserDataUseCase useCase = new FetchUserDataUseCase(this, target, tableName, constraint);
		useCase.run();
	}
	
	public void fetchTemplateNames() {
		FetchTemplateNamesUseCase useCase = new FetchTemplateNamesUseCase(this);
		useCase.run();
	}

	@Override
	public void onSuccessSelectTable(List<String> columns, List<List<String>> data) {
		this.view.displayData(columns, data);
	}

	@Override
	public void onErrorSelectTable(String message) {
		this.view.invalidQuery(message);

	}

	@Override
	public void attachView(CreateUserDataView view) {
		this.view = view;
	}

	@Override
	public void unbindView() {
		this.view = null;
	}

	@Override
	public void onSuccessFetchingNames(List<String> names) throws SelectException {
		view.fillDropdownWithTemplateNames(names);
		
	}

	@Override
	public void onErrorFetchingNames(String errorMessage) {
		view.invalidQuery(errorMessage);
	}


}
