package com.app.presenter;

import com.app.model.interfaces.CreateTemplateModel;
import com.app.model.interfaces.CreateTemplateResultInterface;
import com.app.presenter.interfaces.CreateTemplatePresenter;
import com.app.view.interfaces.CreateTemplateView;

import java.io.File;

public class CreateTemplatePresenterImpl implements CreateTemplatePresenter, CreateTemplateResultInterface {

    private CreateTemplateView view;
    private CreateTemplateModel model;

    public CreateTemplatePresenterImpl(CreateTemplateModel model) {
        this.view = null;
        this.model = model;
    }

    @Override
    public void createTemplateWithFile(File file) {
        if(view.isFileValid()) {
            model.createUsingFile(this, file);
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
}
