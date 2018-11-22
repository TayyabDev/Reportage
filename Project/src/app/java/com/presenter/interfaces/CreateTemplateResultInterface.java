package app.java.com.presenter.interfaces;

import java.util.List;

import app.java.com.model.usecase.CreateTemplateUsingFileUseCase;

public interface CreateTemplateResultInterface {

	void fetchSheetNames(CreateTemplateUsingFileUseCase useCase, List<String> sheetNames);
    void onSuccessCreateTemplate(String message);

    void onErrorCreateTemplate(String message);

}
