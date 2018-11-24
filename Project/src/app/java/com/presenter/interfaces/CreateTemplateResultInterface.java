package app.java.com.presenter.interfaces;

import java.util.List;

import app.java.com.model.usecase.CreateTemplateUsingFileUseCase;

public interface CreateTemplateResultInterface {

	void fetchSheetNames(List<String> sheetNames);
    
	void onSuccessCreateTemplate();

    void onErrorCreateTemplate(String message);
    
    void fetchPKs(List<String> requiredColumnNames);

}
