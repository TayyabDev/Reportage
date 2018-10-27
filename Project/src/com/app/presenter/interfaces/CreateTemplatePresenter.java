package com.app.presenter.interfaces;

import com.app.view.interfaces.CreateTemplateView;

import java.io.File;

public interface CreateTemplatePresenter {

    void createTemplateWithFile(File file);

    void createTemplateWithQuery(String query);

    void attachView(CreateTemplateView view);

    void unbindView();

}
