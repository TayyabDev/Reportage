package app.java.com.view.interfaces;

import java.util.List;

public interface UploadTemplateView {

    void onSuccessTemplateUploaded();

    void onErrorFetchingTemplateNames();

    boolean isFileValid(String filePath);

    void onErrorUploadingFile();

    void onCompatibleTemplateSelected(boolean compatible, int numSheets);

    void fillDropdownWithTemplateNames(List<String> templateNames);

}
