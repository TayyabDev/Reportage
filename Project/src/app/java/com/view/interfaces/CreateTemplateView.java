package app.java.com.view.interfaces;

import java.io.File;
import java.util.List;

public interface CreateTemplateView {

    void onSuccessTemplateCreated();

    boolean isFileValid(String filePath);

    void onErrorUploadingFile();
    
    void displaySheetNames(List<String> sheetNames);
}
