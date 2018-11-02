package app.java.com.presenter.interfaces;

import app.java.com.view.interfaces.UploadTemplateView;

import java.io.File;

public interface UploadTemplatePresenter {

    void uploadTemplateWithFile(String filepath);

    void attachView(UploadTemplateView view);

    void unbindView();




}
