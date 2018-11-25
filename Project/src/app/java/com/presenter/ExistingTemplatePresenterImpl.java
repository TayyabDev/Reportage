package app.java.com.presenter;

import java.util.List;

import app.java.com.model.Exceptions.SelectException;
import app.java.com.model.usecase.FetchTemplateColumnsUseCase;
import app.java.com.model.usecase.FetchTemplateNamesUseCase;
import app.java.com.presenter.interfaces.ExistingTemplatePresenter;
import app.java.com.presenter.interfaces.ExistingTemplateResultInterface;
import app.java.com.presenter.interfaces.FetchTemplateNamesResultInterface;
import app.java.com.view.interfaces.ExistingTemplateView;

public class ExistingTemplatePresenterImpl implements ExistingTemplatePresenter,
		ExistingTemplateResultInterface, FetchTemplateNamesResultInterface {

	private ExistingTemplateView view;


	@Override
	public void fetchTemplateNames() {
		FetchTemplateNamesUseCase useCase = new FetchTemplateNamesUseCase(this);
		useCase.run();
	}

	@Override
	public void fetchTemplateColumns(String template) {
		FetchTemplateColumnsUseCase useCase = new FetchTemplateColumnsUseCase(this, template);
		useCase.run();
	}

	@Override
	public void attachView(ExistingTemplateView view) {
		this.view = view;
	}

	@Override
	public void unbindView() {
		this.view = null;
	}


	@Override
	public void onSuccessFetchingNames(List<String> names) throws SelectException {
		System.out.println(names);
		view.fillDropDownWithTemplates(names);
	}

	@Override
	public void onErrorFetchingNames(String errorMessage) {
		view.onErrorFetchingTemplates();
	}

	@Override
	public void onSuccessSelectTable(List<String> columns) {
		view.displayData(columns);
	}

	@Override
	public void onErrorSelectTable(String message) {
		view.onErrorSelectTemplate(message);

	}
}
