package app.java.com.presenter;

import app.java.com.presenter.interfaces.FetchTemplateNamesResultInterface;
import app.java.com.presenter.interfaces.UploadTemplatePresenter;
import app.java.com.presenter.interfaces.UploadTemplateResultInterface;
import app.java.com.view.interfaces.UploadTemplateView;


import java.io.File;
import java.util.List;

public class UploadTemplatePresenterImpl implements UploadTemplatePresenter, UploadTemplateResultInterface,
        FetchTemplateNamesResultInterface {

    private UploadTemplateView view;

    public UploadTemplatePresenterImpl() {
        this.view = null;
    }

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
    public boolean verifyFileUploaded() {
        return false;
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
}
