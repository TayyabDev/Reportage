package com.app.model.interfaces;

import java.io.File;

public interface CreateTemplateModel {

    void runRawQuery(CreateTemplateResultInterface resultInterface, String query);

    void createUsingFile(CreateTemplateResultInterface resultInterface, String fileName);
}
