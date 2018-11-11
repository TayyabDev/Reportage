package app.java.com.presenter;

import java.util.List;

import app.java.com.model.usecase.FetchUserDataUseCase;
import app.java.com.presenter.interfaces.FetchUserDataPresenter;
import app.java.com.presenter.interfaces.FetchUserDataResultInterface;
import app.java.com.view.interfaces.ViewUserDataView;

public class FetchUserDataPresenterImpl
		implements FetchUserDataPresenter, FetchUserDataResultInterface {

	private ViewUserDataView view;
	
	public FetchUserDataPresenterImpl(ViewUserDataView view) {
		this.view = view;
	} 
	
	@Override
	public void fetchUserDataWithSelection(List<String> target, String tableName, List<String> constraint) {
		FetchUserDataUseCase useCase = new FetchUserDataUseCase(this, target, tableName, constraint);
		useCase.run();

	}

	@Override
	public void onSuccessSelectTable(List<List<String>> data) {
		this.view.displayData(data);
	}

	@Override
	public void onErrorSelectTable(String message) {
		this.view.invalidQuery(message);

	}

	@Override
	public void attachView(ViewUserDataView view) {
		this.view = view;
	}

	@Override
	public void unbindView() {
		this.view = null;
	}

}
