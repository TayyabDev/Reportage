package app.java.com.model.interfaces;

import app.java.com.presenter.interfaces.CreateTemplateResultInterface;

public interface CreateTemplateModel {

    void runRawQuery(CreateTemplateResultInterface resultInterface, String query);

    void createUsingFile(CreateTemplateResultInterface resultInterface, String fileName);
}
