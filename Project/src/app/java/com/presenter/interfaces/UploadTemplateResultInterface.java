package app.java.com.presenter.interfaces;

import java.util.List;

public interface UploadTemplateResultInterface {

    void onSuccessUploadingTemplate();

    void onErrorUploadingTemplate(List<String> errorMessages);
}
