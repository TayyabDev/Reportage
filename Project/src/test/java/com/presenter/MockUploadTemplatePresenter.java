package test.java.com.presenter;

import java.util.Date;
import java.util.List;

import app.java.com.model.entities.account.Account;
import app.java.com.presenter.interfaces.UploadTemplatePresenter;
import app.java.com.view.interfaces.UploadTemplateView;

public class MockUploadTemplatePresenter implements UploadTemplatePresenter {

	@SuppressWarnings("unused")
	private boolean onTemplateSelectedCompatibleReturned = false;
	@SuppressWarnings("unused")
	private boolean onTemplateSelectedReturnValue = false;

	public MockUploadTemplatePresenter() {

	}

	@Override
	public void uploadTemplateWithFile(Date date, String templateName, String filePath,
			int sheetNum, Account account) {

	}

	@Override
	public void attachView(UploadTemplateView view) {

	}

	@Override
	public void fetchTemplateNames() {

	}

	@Override
	public void verifyFileUploaded(String filePath, String templateName, int sheetNum) {

	}

	@Override
	public void unbindView() {

	}

	@Override
	public List<String> fetchSheetNames(String filePath) {
		return null;
	}

}
