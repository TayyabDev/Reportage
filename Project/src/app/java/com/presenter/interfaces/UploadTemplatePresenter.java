package app.java.com.presenter.interfaces;

import app.java.com.view.interfaces.UploadTemplateView;

import java.io.File;
import java.util.List;

public interface UploadTemplatePresenter {

    void uploadTemplateWithFile(String filePath);

    void attachView(UploadTemplateView view);

    List<String> fetchTemplateNames();

    void verifyFileUploaded(String filePath, String templateName);

    void unbindView();

}
