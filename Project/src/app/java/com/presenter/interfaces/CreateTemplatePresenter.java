package app.java.com.presenter.interfaces;

import java.io.File;

import app.java.com.view.interfaces.CreateTemplateView;

public interface CreateTemplatePresenter {

    void createTemplateWithFile(String string);

    void createTemplateWithQuery(String query);

    void attachView(CreateTemplateView view);

    void unbindView();

}
