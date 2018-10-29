package app.java.com.presenter;




import java.io.File;

import app.java.com.model.interfaces.UploadTemplateModel;
import app.java.com.presenter.interfaces.UploadTemplatePresenter;
import app.java.com.view.interfaces.UploadTemplateView;

public class UploadTemplatePresenterImpl implements UploadTemplatePresenter {

    private UploadTemplateView view;
    private UploadTemplateModel model;


    public UploadTemplatePresenterImpl(UploadTemplateModel model){
        this.view = null;
        this.model = model;
    }

    @Override
    public void uploadTemplateWithFile(String filepath) {
        if(view.isFileValid(filepath)){
        model.uploadUsingFile();
        }

    }

    @Override
    public void attachView(UploadTemplateView view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        this.view=null;

    }
}
