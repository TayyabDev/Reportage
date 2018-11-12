package test.java.com.view;

import app.java.com.view.interfaces.UploadTemplateView;
import java.util.List;

public class MockUploadTemplateView implements UploadTemplateView {

    @Override
    public void onSuccessTemplateUploaded() {

    }

    @Override
    public void onErrorFetchingTemplateNames() {

    }

    @Override
    public boolean isFileValid(String filePath) {
        return false;
    }

    @Override
    public void onErrorUploadingFile() {

    }

    @Override
    public void onCompatibleTemplateSelected(boolean compatible) {

    }

    @Override
    public void fillDropdownWithTemplateNames(List<String> templateNames) {

    }
}
