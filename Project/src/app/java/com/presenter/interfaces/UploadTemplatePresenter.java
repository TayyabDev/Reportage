package com.app.presenter.interfaces;

import com.app.view.interfaces.UploadTemplateView;

import java.io.File;

public interface UploadTemplatePresenter {

    void uploadTemplateWithFile(File file);

    void attachView(UploadTemplateView view);

    void unbindView();




}
