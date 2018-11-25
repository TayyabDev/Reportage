package app.java.com.presenter;

import java.util.Date;
import java.util.List;

import app.java.com.model.Exceptions.InsertException;
import app.java.com.model.Exceptions.SelectException;
import app.java.com.model.entities.account.Account;
import app.java.com.model.usecase.FetchTemplateNamesUseCase;
import app.java.com.model.usecase.UploadTemplateUseCase;
import app.java.com.model.usecase.UseCase;
import app.java.com.model.usecase.VerifyTemplateUseCase;
import app.java.com.model.utilities.FileTypeFinder;
import app.java.com.model.utilities.templateFile.TemplateFileCsvImpl;
import app.java.com.model.utilities.templateFile.TemplateFileExcelImpl;
import app.java.com.model.utilities.templateFile.TemplateFileInterface;
import app.java.com.presenter.interfaces.FetchTemplateNamesResultInterface;
import app.java.com.presenter.interfaces.UploadTemplatePresenter;
import app.java.com.presenter.interfaces.UploadTemplateResultInterface;
import app.java.com.presenter.interfaces.VerifyTemplateResultInterface;
import app.java.com.view.interfaces.UploadTemplateView;

public class UploadTemplatePresenterImpl
		implements UploadTemplatePresenter, UploadTemplateResultInterface,
		FetchTemplateNamesResultInterface, VerifyTemplateResultInterface {

	private UploadTemplateView view;
	private int numSheets = 0;

	public UploadTemplatePresenterImpl() {
		this.view = null;
	}

	@Override
	public void uploadTemplateWithFile(Date date, String templateName, String filePath,
			int sheetNum, Account account) {

		// Get the file from the file path
		String formulatedFileName = filePath.replace("\\", "\\\\");

		TemplateFileInterface fileInterface = null;

		if (FileTypeFinder.isCSVFile(formulatedFileName)) {
			fileInterface = new TemplateFileCsvImpl(formulatedFileName);
		} else {
			fileInterface = new TemplateFileExcelImpl(formulatedFileName, sheetNum);
		}

		UseCase uploadTemplateUseCase =
				new UploadTemplateUseCase(this, date, templateName, fileInterface, account);
		uploadTemplateUseCase.run();
	}

	@Override
	public void attachView(UploadTemplateView view) {
		this.view = view;
	}

	@Override
	public void unbindView() {
		this.view = null;
	}

	@Override
	public List<String> fetchSheetNames(String filePath) {

		// Get the file from the file path
		String formulatedFileName = filePath.replace("\\", "\\\\");

		TemplateFileInterface fileInterface = null;

		if (FileTypeFinder.isCSVFile(formulatedFileName)) {
			return null;
		} else {
			fileInterface = new TemplateFileExcelImpl(formulatedFileName);
			return ((TemplateFileExcelImpl) fileInterface).getSheetNames();
		}

	}

	@Override
	public void verifyFileUploaded(String filePath, String templateName, int sheetNum) {

		// Get the file from the file path
		String formulatedFileName = filePath.replace("\\", "\\\\");

		TemplateFileInterface fileInterface = null;

		if (FileTypeFinder.isCSVFile(formulatedFileName)) {
			fileInterface = new TemplateFileCsvImpl(formulatedFileName);
		} else {
			fileInterface = new TemplateFileExcelImpl(formulatedFileName, sheetNum);
		}

		System.out.println("Its working till here");

		UseCase verifyUseCase = new VerifyTemplateUseCase(this, fileInterface, templateName);
		verifyUseCase.run();
	}

	@Override
	public void onSuccessFetchingNames(List<String> names) throws SelectException {
		System.out.println("Fetching Names Successful");
		this.view.fillDropdownWithTemplateNames(names);
	}

	@Override
	public void onErrorFetchingNames(String errorMessage) {
		this.view.onErrorFetchingTemplateNames();
	}

	@Override
	public void fetchTemplateNames() {
		UseCase usecase = new FetchTemplateNamesUseCase(this);
		usecase.run();
	}

	@Override
	public void onTemplateSelectedCompatible(boolean templateValid, TemplateFileInterface file) {
		System.out.println(templateValid + " file valid");
		view.onCompatibleTemplateSelected(templateValid, numSheets);
	}

	@Override
	public void onSuccessUploadingTemplate() {
		this.view.onSuccessTemplateUploaded();
	}

	@Override
	public void onErrorUploadingTemplate(List<InsertException> errorMessages) {
		// move to resolve conflicts view with the errorMessages
		this.view.switchViewToResolveConflicts(errorMessages);
	}

}
