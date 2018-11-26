package app.java.com.view.interfaces;

import java.util.List;

public interface CreateTemplateView {

    void onSuccessTemplateCreated();

    boolean isFileValid(String filePath);

    void onErrorUploadingFile();
    
    void displaySheetNames(List<String> sheetNames);
    
    void displayRequiredColumnNames(List<String> columnNames);
}
