package app.java.com.view.interfaces;

import java.io.File;

public interface CreateTemplateView {

    void onSuccessTemplateCreated();

    boolean isFileValid(String filePath);

    void onErrorUploadingFile();
}
