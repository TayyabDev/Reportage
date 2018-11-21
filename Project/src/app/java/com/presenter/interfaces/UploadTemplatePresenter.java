package app.java.com.presenter.interfaces;

import app.java.com.model.Exceptions.SelectException;
import app.java.com.view.interfaces.UploadTemplateView;

import java.io.File;
import java.util.Date;
import java.util.List;

public interface UploadTemplatePresenter {

    void uploadTemplateWithFile(Date date, String templateName, String filePath, int sheetNum);

    void attachView(UploadTemplateView view);
    
    void fetchTemplateNames();

    void verifyFileUploaded(String filePath, String templateName, int sheetNum);

    void unbindView();

    List<String> fetchSheetNames(String filePath);

}
