package app.java.com.view.interfaces;

import java.util.List;

import app.java.com.model.usecase.UpdateUserDataUseCase.DataChanges;

public interface CreateUserDataView {

	void invalidQuery(String message);

	void displayData(List<String> columns, List<List<String>> data);
	
	void fillDropdownWithTemplateNames(List<String> templateNames); 

	void showDataChanges(DataChanges changes);
	
	void dataUpdateSuccess(String message);
	
	void dataUpdateFail(String message);

}
