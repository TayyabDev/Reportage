package app.java.com.presenter.interfaces;

import app.java.com.model.Exceptions.InsertException;

import java.util.List;

public interface UploadTemplateResultInterface {

    void onSuccessUploadingTemplate();

    void onErrorUploadingTemplate(List<InsertException> errorMessages);
}
