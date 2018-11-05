package app.java.com.presenter;

import app.java.com.model.Exceptions.SelectException;
import app.java.com.model.usecase.FetchTemplateNamesUseCase;
import app.java.com.model.usecase.UseCase;
import app.java.com.presenter.interfaces.FetchTemplateNamesResultInterface;
import app.java.com.presenter.interfaces.UploadTemplatePresenter;
import app.java.com.presenter.interfaces.UploadTemplateResultInterface;
import app.java.com.view.interfaces.UploadTemplateView;
import java.util.List;

public class UploadTemplatePresenterImpl implements UploadTemplatePresenter, UploadTemplateResultInterface,
        FetchTemplateNamesResultInterface {

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
    public void onSuccessUploadingTemplate() {
        this.view.onSuccessTemplateCreated();
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
		// TODO Auto-generated method stub
		UseCase usecase = new FetchTemplateNamesUseCase(this);
		usecase.run();
	}

	@Override
	public void onErrorUploadingTemplate(List<String> errorMessages) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void verifyFileUploaded(String filePath, String templateName) {
		// TODO Auto-generated method stub
		
	}
}
