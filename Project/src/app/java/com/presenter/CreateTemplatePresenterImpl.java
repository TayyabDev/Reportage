package app.java.com.presenter;

import java.io.File;

import app.java.com.model.interfaces.CreateTemplateModel;
import app.java.com.model.interfaces.CreateTemplateResultInterface;
import app.java.com.presenter.interfaces.CreateTemplatePresenter;
import app.java.com.view.interfaces.CreateTemplateView;

public class CreateTemplatePresenterImpl implements CreateTemplatePresenter, CreateTemplateResultInterface, java.com.model.interfaces.NumSheetsInputInterface {

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
        model.runRawQuery(this, query);
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
    public int getNumberOfSheets() throws Exception {
        int sheetNumber = this.view.onGetSheetNumber();
        if(sheetNumber < 0) {
            throw new Exception("Incorrect Sheet Number Provided");
        }

        return sheetNumber;
    }
}
