package app.java.com.presenter.interfaces;

import java.util.List;

import app.java.com.model.Exceptions.InsertException;

public interface UploadTemplateResultInterface {

	void onSuccessUploadingTemplate();

	void onErrorUploadingTemplate(List<InsertException> errorMessages);
}
