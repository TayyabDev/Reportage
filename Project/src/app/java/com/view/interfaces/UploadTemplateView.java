package app.java.com.view.interfaces;

import java.util.List;

public interface UploadTemplateView {

    void onSuccessTemplateUploaded();


    boolean isFileValid(String filePath);

    void onErrorUploadingFile();

    void onCompatibleTemplateSelected(boolean compatible);

    void fillDropdownWithTemplateNames(List<String> templateNames);

}
