package app.java.com.presenter.interfaces;



import java.io.File;

import app.java.com.view.interfaces.UploadTemplateView;

public interface UploadTemplatePresenter {

    void uploadTemplateWithFile(File file);

    void attachView(UploadTemplateView view);

    void unbindView();




}
