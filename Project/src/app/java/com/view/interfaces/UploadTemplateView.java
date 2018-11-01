package app.java.com.view.interfaces;

public interface UploadTemplateView {
    void onSuccessTemplateCreated();

    boolean isFileValid();

    void onErrorUploadingFile();

}
