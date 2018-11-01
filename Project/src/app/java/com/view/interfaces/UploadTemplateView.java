package app.java.com.view.interfaces;

import java.util.List;

public interface UploadTemplateView {

    void onSuccessTemplateCreated();

    boolean isFileValid();

    void onErrorUploadingFile();

    void onInCompatibleTemplateSelected();

    List<String> fillDropdownWithTemplateNames();

}
