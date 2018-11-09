package app.java.com.presenter;

import app.java.com.model.Exceptions.SelectException;
import app.java.com.model.usecase.FetchTemplateNamesUseCase;
import app.java.com.model.usecase.UseCase;
import app.java.com.model.usecase.VerifyTemplateUseCase;
import app.java.com.model.usecase.VerifyTemplateUseCase;
import app.java.com.presenter.interfaces.FetchTemplateNamesResultInterface;
import app.java.com.presenter.interfaces.UploadTemplatePresenter;
import app.java.com.presenter.interfaces.UploadTemplateResultInterface;
import app.java.com.presenter.interfaces.VerifyTemplateResultInterface;
import app.java.com.presenter.interfaces.VerifyTemplateResultInterface;
import app.java.com.view.interfaces.UploadTemplateView;
import java.util.List;

public class UploadTemplatePresenterImpl implements UploadTemplatePresenter, UploadTemplateResultInterface,
        FetchTemplateNamesResultInterface, VerifyTemplateResultInterface {

    private UploadTemplateView view;

    public UploadTemplatePresenterImpl() {
        this.view = null;
    }

    @Override
    public void uploadTemplateWithFile(String filePath) {


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
	public void verifyFileUploaded(String filePath, String templateName) {
		UseCase verifyUseCase = new VerifyTemplateUseCase(this, filePath, templateName);
		verifyUseCase.run();
	}

	@Override
	public void onSuccessFetchingNames(List<String> names) throws SelectException {
		this.view.fillDropdownWithTemplateNames(names);
	}

	@Override
	public String onErrorFetchingNames(String errorMessage) {
		return errorMessage;
	}

	@Override
	public void fetchTemplateNames() {
		UseCase usecase = new FetchTemplateNamesUseCase(this);
		usecase.run();
	}

    @Override
    public void onTemplateSelectedCompatible(boolean templateValid) {
        if(templateValid) {
            view.onCompatibleTemplateSelected(templateValid);
        }
    }

    @Override
    public void onSuccessUploadingTemplate() {
        this.view.onSuccessTemplateCreated();
    }

	@Override
	public void onErrorUploadingTemplate(List<String> errorMessages) {
		// TODO Auto-generated method stub

	}

}