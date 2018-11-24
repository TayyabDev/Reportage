package app.java.com.view.interfaces;

import app.java.com.model.Exceptions.InsertException;

import java.util.List;

public interface UploadTemplateView {

    void onSuccessTemplateUploaded();

    void onErrorFetchingTemplateNames();

    boolean isFileValid(String filePath);

    void onErrorUploadingFile();

    void onCompatibleTemplateSelected(boolean compatible, int numSheets);

    void fillDropdownWithTemplateNames(List<String> templateNames);

    void switchViewToResolveConflicts(List<InsertException> exceptions);

}
