package com.app.presenter.interfaces;

import java.io.File;

public interface CreateTemplatePresenter {

    void createTemplate(File file);

    void onSuccess();

    void onError();

}
