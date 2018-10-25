package com.app.model;

import com.app.model.interfaces.CreateTemplateResultInterface;

import java.io.File;

public class CreateTemplateModelImpl implements com.app.model.interfaces.CreateTemplateModel {

    @Override
    public void runRawQuery(CreateTemplateResultInterface templateResultInterface, String query) {
        // Add implementation for the query here

        // Look at templateResultInterface for communication back with the presenter
    }

    @Override
    public void createUsingFile(CreateTemplateResultInterface templateResultInterface, File file) {
        // Add implementation for the file over here

        // Look at templateResultInterface for communication back with the presenter
    }
}
