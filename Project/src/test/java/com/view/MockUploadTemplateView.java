package test.java.com.view;

import java.util.List;

import app.java.com.model.Exceptions.InsertException;
import app.java.com.view.interfaces.UploadTemplateView;

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
	public void onCompatibleTemplateSelected(boolean compatible, int numSheets) {

	}

	@Override
	public void fillDropdownWithTemplateNames(List<String> templateNames) {

	}

	@Override
	public void switchViewToResolveConflicts(List<InsertException> exceptions) {

	}
}
