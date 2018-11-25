package app.java.com.presenter.interfaces;

import java.util.Date;
import java.util.List;

import app.java.com.model.entities.account.Account;
import app.java.com.view.interfaces.UploadTemplateView;

public interface UploadTemplatePresenter {

	void uploadTemplateWithFile(Date date, String templateName, String filePath, int sheetNum,
			Account account);

	void attachView(UploadTemplateView view);

	void fetchTemplateNames();

	void verifyFileUploaded(String filePath, String templateName, int sheetNum);

	void unbindView();

	List<String> fetchSheetNames(String filePath);

}
