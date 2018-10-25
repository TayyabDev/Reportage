package com.app.view.interfaces;

public interface CreateTemplateView {

    void onSuccessTemplateCreated();

    boolean isFileValid();

    void onErrorUploadingFile();
}
