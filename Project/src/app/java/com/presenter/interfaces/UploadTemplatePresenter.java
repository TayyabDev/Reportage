package app.java.com.presenter.interfaces;

import app.java.com.model.Exceptions.SelectException;
import app.java.com.view.interfaces.UploadTemplateView;

import java.io.File;
import java.util.List;

public interface UploadTemplatePresenter {

    void uploadTemplateWithFile(String filePath);

    void attachView(UploadTemplateView view);
    
    void fetchTemplateNames();

    boolean verifyFileUploaded();

    void unbindView();

}
