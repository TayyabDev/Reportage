package app.java.com.presenter;

import app.java.com.model.usecase.UseCase;
import app.java.com.model.usecase.VerifyTemplateUseCase;
import app.java.com.presenter.interfaces.FetchTemplateNamesResultInterface;
import app.java.com.presenter.interfaces.UploadTemplatePresenter;
import app.java.com.presenter.interfaces.UploadTemplateResultInterface;
import app.java.com.presenter.interfaces.VerifyTemplateResultInterface;
import app.java.com.view.interfaces.UploadTemplateView;


import java.io.File;
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
    public List<String> fetchTemplateNames() {
        return null;
    }

    @Override
    public void verifyFileUploaded(String filePath, String templateName) {
        UseCase verifyUseCase = new VerifyTemplateUseCase(this, filePath, templateName);
        verifyUseCase.run();
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
    public void onErrorUploadingTemplate() {
        this.view.onErrorUploadingFile();
    }

    @Override
    public List<String> onSuccessFetchingNames() {
        return null;
    }

    @Override
    public String onErrorFetchingNames() {
        return null;
    }

    @Override
    public void onTemplateSelectedCompatible(boolean templateValid) {
        this.view.onInCompatibleTemplateSelected(templateValid);
    }
}
