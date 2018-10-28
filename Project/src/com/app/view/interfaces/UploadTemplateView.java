package com.app.view.interfaces;

public interface UploadTemplateView {
    void onSuccessTemplateCreated();

    boolean isFileValid();

    void onErrorUploadingFile();

}
