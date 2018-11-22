package app.java.com.presenter;

import java.util.List;

import app.java.com.model.interfaces.CreateTemplateModel;
import app.java.com.model.usecase.CreateTemplateUsingFileUseCase;
import app.java.com.model.usecase.CreateTemplateWithQueryUseCase;
import app.java.com.presenter.interfaces.CreateTemplateResultInterface;
import app.java.com.presenter.interfaces.CreateTemplatePresenter;
import app.java.com.view.interfaces.CreateTemplateView;
import app.java.com.model.usecase.UseCase;

public class CreateTemplatePresenterImpl implements CreateTemplatePresenter, CreateTemplateResultInterface {

    private CreateTemplateView view;
    private CreateTemplateUsingFileUseCase usingFileUseCase;

    @Override
    public void createTemplateWithFile(String filePath) {
        if(view.isFileValid(filePath)) {
        	// create UseCase to implements this
        	usingFileUseCase = new CreateTemplateUsingFileUseCase(this, filePath);
        	usingFileUseCase.run();
//            model.createUsingFile(this, filePath);
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
    public void onSuccessCreateTemplate(String message) {
        view.onSuccessTemplateCreated();
    }

    @Override
    public void onErrorCreateTemplate(String message) {
        view.onErrorUploadingFile();
    }

	@Override
	public void fetchSheetNames(CreateTemplateUsingFileUseCase useCase, List<String> sheetNames) {
		view.displaySheetNames(sheetNames);
	}

	@Override
	public void sheetSelected(String sheetName) {
		usingFileUseCase.sheetSelected(sheetName);
	}
	
	
}
