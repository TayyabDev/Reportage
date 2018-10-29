package com.app.presenter;

import com.app.model.interfaces.UploadTemplateModel;
import com.app.presenter.interfaces.UploadTemplatePresenter;
import com.app.view.interfaces.UploadTemplateView;


import java.io.File;

public class UploadTemplatePresenterImpl implements UploadTemplatePresenter {

    private UploadTemplateView view;
    private UploadTemplateModel model;


    public UploadTemplatePresenterImpl(UploadTemplateModel model){
        this.view = null;
        this.model = model;
    }

    @Override
    public void uploadTemplateWithFile(File file) {
        if(view.isFileValid()){
        model.uploadUsingFile();
        }

    }

    @Override
    public void attachView(UploadTemplateView view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        this.view=null;

    }
}
