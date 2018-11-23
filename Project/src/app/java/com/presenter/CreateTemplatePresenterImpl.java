package app.java.com.presenter;

import java.util.List;

import app.java.com.model.entities.account.TeqAccount;
import app.java.com.model.usecase.CreateTemplateUsingFileUseCase;
import app.java.com.model.usecase.CreateTemplateWithQueryUseCase;
import app.java.com.presenter.interfaces.CreateTemplateResultInterface;
import app.java.com.presenter.interfaces.CreateTemplatePresenter;
import app.java.com.view.interfaces.CreateTemplateView;
import app.java.com.model.usecase.UseCase;

public class CreateTemplatePresenterImpl implements CreateTemplatePresenter, CreateTemplateResultInterface{

    private CreateTemplateView view;
    private CreateTemplateUsingFileUseCase usingFileUseCase;

    @Override
    public void createTemplateWithFile(String filePath, TeqAccount account) {
        if(view.isFileValid(filePath)) {
        	usingFileUseCase = new CreateTemplateUsingFileUseCase(this, filePath, account);
        	usingFileUseCase.run();
        }
    }

    @Override
    public void createTemplateWithQuery(String query) {
        UseCase usecase = new CreateTemplateWithQueryUseCase(this, query);
        usecase.run();
    }

    @Override
    public void attachView(CreateTemplateView view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        this.view = null;
    }

    @Override
    public void onSuccessCreateTemplate() {
        view.onSuccessTemplateCreated();
    }

    @Override
    public void onErrorCreateTemplate(String message) {
        view.onErrorUploadingFile();
    }

	@Override
	public void fetchSheetNames(List<String> sheetNames) {
		view.displaySheetNames(sheetNames);
	}

	@Override
	public void sheetSelected(String sheetName) {
		usingFileUseCase.sheetSelected(sheetName);
	}

	@Override
	public void fetchPKs(List<String> columnNames) {
		view.displayRequiredColumnNames(columnNames);
	}

	@Override
	public void PKSelected(List<String> pks) {
		usingFileUseCase.PKselected(pks);
	}
	
	
}
