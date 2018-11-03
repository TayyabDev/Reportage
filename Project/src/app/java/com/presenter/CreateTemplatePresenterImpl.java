package app.java.com.presenter;

import app.java.com.model.interfaces.CreateTemplateModel;
import app.java.com.model.usecase.CreateTemplateWithQueryUseCase;
import app.java.com.presenter.interfaces.CreateTemplateResultInterface;
import app.java.com.presenter.interfaces.CreateTemplatePresenter;
import app.java.com.view.interfaces.CreateTemplateView;
import app.java.com.model.usecase.UseCase;

public class CreateTemplatePresenterImpl implements CreateTemplatePresenter, CreateTemplateResultInterface {

    private CreateTemplateView view;
    private CreateTemplateModel model;

    public CreateTemplatePresenterImpl(CreateTemplateModel model) {
        this.view = null;
        this.model = model;
    }

    @Override
    public void createTemplateWithFile(String filePath) {
        if(view.isFileValid(filePath)) {
            model.createUsingFile(this, filePath);
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
}
