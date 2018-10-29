package app.java.com.presenter.interfaces;



import java.io.File;

import app.java.com.view.interfaces.UploadTemplateView;

public interface UploadTemplatePresenter {

    void uploadTemplateWithFile(String filepath);

    void attachView(UploadTemplateView view);

    void unbindView();




}
