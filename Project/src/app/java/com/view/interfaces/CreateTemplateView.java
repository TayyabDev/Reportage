package app.java.com.view.interfaces;

public interface CreateTemplateView {

    void onSuccessTemplateCreated();

    boolean isFileValid();

    void onErrorUploadingFile();
}
