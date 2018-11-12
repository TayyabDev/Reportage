package test.java.com.presenter;

import app.java.com.presenter.interfaces.UploadTemplatePresenter;
import app.java.com.presenter.interfaces.VerifyTemplateResultInterface;
import app.java.com.view.interfaces.UploadTemplateView;
import java.util.Date;

public class MockUploadTemplatePresenter implements UploadTemplatePresenter{

    private boolean onTemplateSelectedCompatibleReturned = false;
    private boolean onTemplateSelectedReturnValue = false;

    public MockUploadTemplatePresenter() {

    }

    @Override
    public void uploadTemplateWithFile(Date date, String templateName, String filePath) {

    }

    @Override
    public void attachView(UploadTemplateView view) {

    }

    @Override
    public void fetchTemplateNames() {

    }

    @Override
    public void verifyFileUploaded(String filePath, String templateName) {

    }

    @Override
    public void unbindView() {

    }
}
